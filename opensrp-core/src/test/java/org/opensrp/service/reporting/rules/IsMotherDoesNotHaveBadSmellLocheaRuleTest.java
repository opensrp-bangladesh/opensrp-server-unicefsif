package org.opensrp.service.reporting.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.opensrp.common.util.EasyMap.create;

import org.junit.Before;
import org.junit.Test;
import org.opensrp.util.SafeMap;


public class IsMotherDoesNotHaveBadSmellLocheaRuleTest {
    private IsMotherDoesNotHaveBadSmellLocheaRule rule;

    @Before
    public void setUp() throws Exception {
        rule = new IsMotherDoesNotHaveBadSmellLocheaRule();
    }

    @Test
    public void shouldReturnFalseIfMotherHasBadSmellLochea() {
        SafeMap safeMap = new SafeMap(create("vaginalProblems", "heavy_bleeding bad_smell_lochea").map());

        boolean didRuleApply = rule.apply(safeMap);
        assertFalse(didRuleApply);
    }

    @Test
    public void shouldReturnTrueIfMotherDoesNotHaveBadSmellLochea() {

        boolean didRuleApply = rule.apply(new SafeMap(create("vaginalProblems", "heavy_bleeding").map()));
        assertTrue(didRuleApply);
        
        didRuleApply = rule.apply(new SafeMap().put("vaginalProblems", null));
        assertTrue(didRuleApply);
    }
}
