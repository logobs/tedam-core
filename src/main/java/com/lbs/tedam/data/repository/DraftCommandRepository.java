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

import com.lbs.tedam.model.DraftCommand;
import com.lbs.tedam.model.Project;

import java.util.List;

/**
 * Repository for entity DraftCommand.
 */
public interface DraftCommandRepository extends BaseRepository<DraftCommand, Integer> {

    /**
     * List draft commands which are active and project id.
     *
     * @param projectId Project of DraftCommand.
     * @return List of DraftCommand.
     */
    public List<DraftCommand> findByProjectAndDeleted(Project project, boolean deleted);

}
