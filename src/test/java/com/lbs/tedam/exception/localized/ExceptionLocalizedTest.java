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

package com.lbs.tedam.exception.localized;

import com.lbs.tedam.data.config.DataConfig;
import com.lbs.tedam.data.service.TestDataConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDataConfig.class, DataConfig.class})
public class ExceptionLocalizedTest {

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testJobDeleteException() {
        JobDeleteException jobDeleteException = new JobDeleteException();
    }

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testLocalizedException() {
        LocalizedException localizedException = new LocalizedException("resourceId");
    }

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testSnapshotFileNotFoundException() {
        SnapshotFileNotFoundException snapshotFileNotFoundException = new SnapshotFileNotFoundException("fileName");
    }

    /**
     * @author Seyma.Sahin
     */
    @Test
    public void testTestSetDeleteException() {
        TestSetDeleteException testSetDeleteException = new TestSetDeleteException("message");
    }
}
