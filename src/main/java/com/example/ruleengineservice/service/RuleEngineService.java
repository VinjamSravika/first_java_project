package com.example.ruleengineservice.service;

import com.example.ruleengineservice.model.EvaluationResult;
import com.example.ruleengineservice.rules.TradingRule;
import org.springframework.stereotype.Service;

@Service
public class RuleEngineService {

    public EvaluationResult evaluateRule(TradingRule rule, double currentPrice) {
        boolean isTriggered = false;

        if ("BUY".equalsIgnoreCase(rule.getAction())) {
            isTriggered = currentPrice < rule.getTriggerPrice();
        } else if ("SELL".equalsIgnoreCase(rule.getAction())) {
            isTriggered = currentPrice > rule.getTriggerPrice();
        }

        return new EvaluationResult(
                rule.getSymbol(),
                currentPrice,
                rule.getTriggerPrice(),
                rule.getAction(),
                isTriggered
        );
    }
}
