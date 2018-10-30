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

package com.lbs.tedam.data.service;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.impl.JobParameterValueServiceImpl;
import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.JobParameterValue;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = {JobParameterValueServiceImpl.class, TestDataConfig.class, DataConfig.class})
public class JobParameterValueServiceTest extends BaseServiceTest {

    @Autowired
    private JobParameterValueService jobParameterValueService;

    @Test
    public void testSaveJobParameterValue() throws LocalizedException {
        JobParameterValue jobParameterValue = new JobParameterValue("denemeValue", null);
        jobParameterValueService.save(jobParameterValue);
    }

}
