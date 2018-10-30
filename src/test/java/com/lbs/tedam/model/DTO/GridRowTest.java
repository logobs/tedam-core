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

package com.lbs.tedam.model.DTO;

import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;

import java.util.ArrayList;

public class GridRowTest extends BaseServiceTest {

    /**
     * this method test02GettersAndSetters <br>
     *
     * @author Tarik.Mikyas <br>
     */
    @Test
    public void test02GettersAndSetters() {
        GridRow gridRow = new GridRow();
        gridRow.setCells(new ArrayList<>());
        gridRow.add(null);
        gridRow.setRowIndex(6);
        gridRow.getCells();
        gridRow.getRowIndex();
        gridRow.getSize();
    }

}
