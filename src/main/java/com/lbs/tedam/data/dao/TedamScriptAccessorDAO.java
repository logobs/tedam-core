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

package com.lbs.tedam.data.dao;

import com.lbs.tedam.exception.localized.LocalizedException;
import com.lbs.tedam.model.TedamScriptAccessor;
import com.lbs.tedam.util.EnumsV2.ScriptAccessorOperationType;
import com.lbs.tedam.util.EnumsV2.ScriptAccessorType;

import java.util.List;

/**
 * @author Canberk.Erkmen
 */
public interface TedamScriptAccessorDAO extends BaseDAO<TedamScriptAccessor, Integer> {

    /**
     * this method getTedamScriptAccessorsByScriptAndOperationType accessor based on script type and processing.<br>
     *
     * @param scriptAccessorType
     * @param scriptAccessorOperationType
     * @return <br>
     * @throws LocalizedException
     * @author Canberk.Erkmen
     */
    public List<TedamScriptAccessor> getTedamScriptAccessorListByScriptAndOperationType(ScriptAccessorType scriptAccessorType,
                                                                                        ScriptAccessorOperationType scriptAccessorOperationType) throws LocalizedException;
}
