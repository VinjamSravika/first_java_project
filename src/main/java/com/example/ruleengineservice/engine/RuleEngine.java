
package com.example.ruleengineservice.engine;

import com.example.ruleengineservice.rules.TradingRule;

public class RuleEngine {
    public static boolean evaluateRule(TradingRule rule, double currentPrice) {
        if ("BUY".equalsIgnoreCase(rule.getAction())) {
            return currentPrice > rule.getTriggerPrice();
        } else if ("SELL".equalsIgnoreCase(rule.getAction())) {
            return currentPrice < rule.getTriggerPrice();
        }
        return false;
    }
}

