/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.data.service.impl;

import com.lbs.tedam.data.dao.TestSetDAO;
import com.lbs.tedam.data.service.JobDetailService;
import com.lbs.tedam.data.service.TedamFolderService;
import com.lbs.tedam.data.service.TestCaseTestRunService;
import com.lbs.tedam.data.service.TestSetService;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.exception.localized.TestSetDeleteException;
import com.lbs.tedam.model.*;
import com.lbs.tedam.util.Constants;
import com.lbs.tedam.util.EnumsV2.CommandStatus;
import com.lbs.tedam.util.EnumsV2.TedamBoolean;
import com.lbs.tedam.util.EnumsV2.TedamFolderType;
import com.lbs.tedam.util.TedamStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestSetServiceImpl extends BaseServiceImpl<TestSet, Integer> implements TestSetService {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    private TestSetDAO dao;
    private JobDetailService jobDetailService;
    private TedamFolderService folderService;
    private TestCaseTestRunService testCaseRunService;

    @Autowired
    public void setDao(TestSetDAO dao, JobDetailService jobDetailService, TedamFolderService folderService, TestCaseTestRunService testCaseRunService) {
        this.dao = dao;
        this.jobDetailService = jobDetailService;
        this.folderService = folderService;
        this.testCaseRunService = testCaseRunService;
        super.setBaseDao(dao);
    }

    @Override
    public List<TestSet> getTestSetListByProject(Project project) throws LocalizedException {
        List<TestSet> testSetList = dao.getTestSetListByProject(project);
        rebuildTestSetListWithJobId(project, testSetList);
        return testSetList;
    }

    @Override
    public List<TestSet> getTestSetListWithJobIdByProjectAndFolder(Project project, TedamFolder folder) throws LocalizedException {
        List<TestSet> testSetList = dao.getTestSetListByProjectAndFolder(project, folder);
        rebuildTestSetListWithJobId(project, testSetList);
        return testSetList;
    }

    @Override
    public List<TestSet> getTestSetListWithJobIdByProject(Project project) throws LocalizedException {
        List<TestSet> testSetList = dao.getTestSetListByProject(project);
        rebuildTestSetListWithJobId(project, testSetList);
        return testSetList;
    }

    private void rebuildTestSetListWithJobId(Project project, List<TestSet> testSetList) throws LocalizedException {
        List<JobDetail> jobDetailListByProject = jobDetailService.getJobDetailListByProject(project);
        for (TestSet testSet : testSetList) {
            List<JobDetail> jobDetailList = jobDetailListByProject.stream().filter(jobDetail -> jobDetail.getTestSetId().equals(testSet.getId())).collect(Collectors.toList());
            String jobListAsString = TedamStringUtils
                    .getListAsStringWithSeparator(jobDetailList.stream().map(jobDetail -> String.valueOf(jobDetail.getJobId())).collect(Collectors.toList()), Constants.TEXT_COMMA);
            testSet.setJobListAsString(jobListAsString);
        }
        List<TedamFolder> folderList = folderService.getTedamFolderListByProjectAndFolderType(project, TedamFolderType.TESTSET);
        setTestCaseFolderName(testSetList, folderList);
        setTestSetExecutionStatus(testSetList);
    }

    private void setTestSetExecutionStatus(List<TestSet> testSetList) throws LocalizedException {
        int size = testSetList.size();
        if (size > 0) {
            Integer endId = testSetList.get(0).getId();
            Integer startId = testSetList.get(size - 1).getId();
            List<TestCaseTestRun> runList = testCaseRunService.findByTestSetIdRange(startId, endId, TedamBoolean.FALSE.getBooleanValue());
            List<Object[]> testSetTestCaseList = dao.findByTestSetIdRange(startId, endId, TedamBoolean.FALSE.getBooleanValue());
            countTestCaseStatus(testSetList, testSetTestCaseList, runList);
        }
    }

    private void countTestCaseStatus(List<TestSet> testSetList, List<Object[]> testSetTestCaseList,
                                     List<TestCaseTestRun> runList) {
        for (TestSet testSet : testSetList) {
            int failedTestCaseCount = 0;
            int succeededTestCaseCount = 0;
            int notRunTestCaseCount = 0;
            int blockedTestCaseCount = 0;
            int cautionTestCaseCount = 0;

            for (Object[] testSetTestCase : testSetTestCaseList) {
                Integer testSetId = (Integer) testSetTestCase[0];
                if (!testSetId.equals(testSet.getId())) {
                    continue;
                }
                Integer testCaseId = (Integer) testSetTestCase[1];
                for (TestCaseTestRun run : runList) {
                    if (testSetId.equals(run.getTestSetId()) && testCaseId.equals(run.getTestCaseId())) {
                        switch (run.getExecutionStatus()) {
                            case FAILED:
                                failedTestCaseCount++;
                                break;
                            case SUCCEEDED:
                                succeededTestCaseCount++;
                                break;
                            case NOTRUN:
                                notRunTestCaseCount++;
                                break;
                            case BLOCKED:
                                blockedTestCaseCount++;
                                break;
                            case CAUTION:
                                cautionTestCaseCount++;
                                break;
                            default:
                                break;
                        }
                        break;
                    }
                }
            }

            testSet.setFailedTestCaseCount(failedTestCaseCount);
            testSet.setSucceededTestCaseCount(succeededTestCaseCount);
            testSet.setNotRunTestCaseCount(notRunTestCaseCount);
            testSet.setBlockedTestCaseCount(blockedTestCaseCount);
            testSet.setCautionTestCaseCount(cautionTestCaseCount);
        }
    }

    private void setTestCaseFolderName(List<TestSet> testSetList, List<TedamFolder> folderList) {
        for (TestSet testSet : testSetList) {
            if (testSet.getTestSetFolderId() != null) {
                for (TedamFolder folder : folderList) {
                    if (testSet.getTestSetFolderId().equals(folder.getId())) {
                        testSet.setTestSetFolder(folder.getName());
                        break;
                    }
                }
            }
        }
    }

    @Override
    public TestSet getTestSetByName(String name) throws LocalizedException {
        return dao.getTestSetByName(name);
    }

    @Override
    public void deleteTestSetFolderId(Integer testSetFolderId) throws LocalizedException {
        dao.deleteTestSetFolderId(testSetFolderId);
    }

    @Override
    public boolean isTestSetInProgressStatus(TestSet testSet) {
        boolean isInProgressStatus = (testSet != null && CommandStatus.IN_PROGRESS.equals(testSet.getTestSetStatus())) ? true : false;
        return isInProgressStatus;
    }

    @Override
    public void beforeDelete(Integer id) throws LocalizedException {
        TestSet testSet = getById(id);
        List<JobDetail> jobDetailList = jobDetailService.getJobDetailListByTestSet(testSet);
        if (!jobDetailList.isEmpty()) {
            String message = jobDetailList.stream().map(jobDetail -> jobDetail.getJobId().toString()).collect(Collectors.joining(Constants.TEXT_COMMA));
            throw new TestSetDeleteException(message);
        }
        super.beforeDelete(id);
    }

    @Override
    public void resetTestSet(Integer jobId) throws LocalizedException {
        dao.resetTestSet(jobId);
    }
}