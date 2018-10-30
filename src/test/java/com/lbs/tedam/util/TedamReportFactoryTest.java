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

package com.lbs.tedam.util;

import com.lbs.tedam.bsh.utils.ScriptService;
import com.lbs.tedam.model.TestReport;
import com.lbs.tedam.test.BaseServiceTest;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author Tarik.Mikyas<br>
 */
public class TedamReportFactoryTest extends BaseServiceTest {

    @Test
    public void test01CompareTwoPDFFile() {
        File pdfTargetFile = new File("c:\\\\pdfCompare\\\\MalzemeVeHizmetListesi.pdf");
        File pdfSourceFile = new File("c:\\pdfCompare\\MalzemeVeHizmetListesi_old.pdf");
        TedamReportUtils factory = new TedamReportUtils();
        TestReport report = factory.compareTwoPDFFile(pdfSourceFile, pdfTargetFile);
        assertNotNull(report);
        List<TestReport> listTestReport = new ArrayList<TestReport>();
        listTestReport.add(report);
        new ScriptService().printTestReport(listTestReport, "raporDosyamiz6.xls", PropUtils.getProperty(Constants.TEMP_FILE_PATH) + Constants.FILE_SEPARATOR);
    }

}
