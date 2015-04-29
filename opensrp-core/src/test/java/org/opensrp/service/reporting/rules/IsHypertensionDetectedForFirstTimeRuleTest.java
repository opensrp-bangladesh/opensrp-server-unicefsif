package org.opensrp.service.reporting.rules;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.opensrp.common.util.EasyMap.create;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.opensrp.util.SafeMap;

public class IsHypertensionDetectedForFirstTimeRuleTest {

    private IsHypertensionDetectedForFirstTimeRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new IsHypertensionDetectedForFirstTimeRule();
    }

    @Test
    public void shouldReturnTrueWhenHyperTensionDetected() {
        Map<String, String> reportFields =
                create("id", "mother id 1")
                        .put("isHypertensionDetectedForFirstTime", "true")
                        .map();

        assertTrue(rule.apply(new SafeMap(reportFields)));
    }

    @Test
    public void shouldReturnFalseWhenHyperTensionDetected() {
        Map<String, String> reportFields = create("id", "mother id 1")
                .put("isHypertensionDetectedForFirstTime", "false")
                .map();

        assertFalse(rule.apply(new SafeMap(reportFields)));
    }
}
