/**
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.job.integrate.std;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dangdang.ddframe.job.integrate.AbstractEnabledBaseStdJobTest;
import com.dangdang.ddframe.job.integrate.fixture.SequencePerpetualElasticJob;
import com.dangdang.ddframe.job.internal.statistics.ProcessCountStatistics;
import com.dangdang.ddframe.test.WaitingUtils;

public final class SequencePerpetualElasticJobTest extends AbstractEnabledBaseStdJobTest {
    
    public SequencePerpetualElasticJobTest() {
        super(SequencePerpetualElasticJob.class);
    }
    
    @Before
    @After
    public void reset() {
        SequencePerpetualElasticJob.reset();
    }
    
    @Test
    public void assertJobInit() {
        initJob();
        assertRegCenterCommonInfo();
        while (!SequencePerpetualElasticJob.isCompleted()) {
            WaitingUtils.waitingShortTime();
        }
        assertTrue(getRegCenter().isExisted("/testJob/execution"));
        assertThat(ProcessCountStatistics.getProcessSuccessCount("testJob"), is(30));
        assertThat(ProcessCountStatistics.getProcessFailureCount("testJob"), is(0));
    }
}
