package org.opensrp.service.reporting.rules;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.opensrp.common.util.EasyMap.create;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.opensrp.util.SafeMap;

public class IsHypertensionStateChangedRuleTest {

    private IsHypertensionStateChangedRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new IsHypertensionStateChangedRule();
    }

    @Test
    public void shouldReturnTrueWhenHyperTensionStateChanged() {
        Map<String, String> reportFields =
                create("id", "mother id 1")
                        .put("bpSystolic","140")
                        .put("bpDiastolic", "90")
                        .put("previousBpSystolic", "130")
                        .put("previousBpDiastolic", "80")
                        .map();

        assertTrue(rule.apply(new SafeMap(reportFields)));

        reportFields =
                create("id", "mother id 1")
                        .put("bpSystolic","140")
                        .put("bpDiastolic", "80")
                        .put("previousBpSystolic", "130")
                        .put("previousBpDiastolic", "80")
                        .map();

        assertTrue(rule.apply(new SafeMap(reportFields)));


        reportFields =
                create("id", "mother id 1")
                        .put("bpSystolic","120")
                        .put("bpDiastolic", "90")
                        .put("previousBpSystolic", "130")
                        .put("previousBpDiastolic", "80")
                        .map();

        assertTrue(rule.apply(new SafeMap(reportFields)));
    }

    @Test
    public void shouldReturnFalseWhenHyperTensionStateIsNotChanged() {
        Map<String, String> reportFields =
                create("id", "mother id 1")
                        .put("bpSystolic", "125")
                        .put("bpDiastolic", "80")
                        .put("previousBpSystolic", "130")
                        .put("previousBpDiastolic", "80")

                        .map();

        assertFalse(rule.apply(new SafeMap(reportFields)));
    }
}
