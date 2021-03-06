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

package com.lbs.tedam.data.repository;

import java.util.List;

import com.lbs.tedam.model.JobGroup;
import com.lbs.tedam.model.Project;
import com.lbs.tedam.util.EnumsV2.JobStatus;

public interface JobGroupRepository extends BaseRepository<JobGroup, Integer> {

	public List<JobGroup> findByProjectAndDeleted(Project project, boolean deleted);

	public List<JobGroup> findByProjectAndDeletedAndActive(Project project, boolean deleted, boolean active);

	public List<JobGroup> findByProjectAndDeletedAndStatus(Project project, boolean deleted, JobStatus jobStatus);
}
