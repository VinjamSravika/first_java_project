package com.example.ruleengineservice.rules;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.ruleengineservice.engine.RuleEngine;
public class RuleEngineTest {

    @Test
    public void testBuyRuleTriggered() {
        TradingRule rule = new TradingRule("AAPL", 220.0, "BUY");
        boolean result = RuleEngine.evaluateRule(rule, 210.0);
        assertTrue(result);
    }

    @Test
    public void testBuyRuleNotTriggered() {
        TradingRule rule = new TradingRule("AAPL", 210.0, "BUY");
        boolean result = RuleEngine.evaluateRule(rule, 215.0);
        assertFalse(result);
    }

    @Test
    public void testSellRuleTriggered() {
        TradingRule rule = new TradingRule("AAPL", 210.0, "SELL");
        boolean result = RuleEngine.evaluateRule(rule, 220.0);
        assertTrue(result);
    }

    @Test
    public void testSellRuleNotTriggered() {
        TradingRule rule = new TradingRule("AAPL", 220.0, "SELL");
        boolean result = RuleEngine.evaluateRule(rule, 215.0);
        assertFalse(result);
    }
}
