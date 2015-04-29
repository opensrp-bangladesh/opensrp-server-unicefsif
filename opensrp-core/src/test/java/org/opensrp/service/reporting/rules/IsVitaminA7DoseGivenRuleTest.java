package org.opensrp.service.reporting.rules;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.opensrp.common.util.EasyMap.create;

import org.junit.Before;
import org.junit.Test;
import org.opensrp.util.SafeMap;


public class IsVitaminA7DoseGivenRuleTest {
    private IsVitaminA7DoseGivenRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new IsVitaminA7DoseGivenRule();
    }

    @Test
    public void shouldReturnTrueIfVitaminA7DoseIsGiven() {
        SafeMap safeMap = new SafeMap(create("vitaminADose", "7").map());

        boolean didRuleApply = rule.apply(safeMap);
        assertTrue(didRuleApply);
    }

    @Test
    public void shouldReturnFalseIfVitaminA7DoseIsNotGiven() {

        boolean didRuleApply = rule.apply(new SafeMap(create("vitaminADose", "1").map()));
        assertFalse(didRuleApply);

        didRuleApply = rule.apply(new SafeMap(create("vitaminADose", "").map()));
        assertFalse(didRuleApply);
    }
}
