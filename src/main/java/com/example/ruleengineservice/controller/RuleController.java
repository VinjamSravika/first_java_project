package com.example.ruleengineservice.controller;
import com.example.ruleengineservice.model.EvaluationResult;
import com.example.ruleengineservice.engine.RuleEngine;
import com.example.ruleengineservice.rules.TradingRule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/rules")
@CrossOrigin(origins = "*")
public class RuleController {

    private final List<TradingRule> rules = new ArrayList<>();

    public RuleController() {
        rules.add(new TradingRule("AAPL", 210.0, "BUY"));
        rules.add(new TradingRule("MSFT", 350.0, "BUY"));
        rules.add(new TradingRule("AAPL", 220.0, "SELL"));
    }

    @GetMapping
    public List<TradingRule> getRules() {
        return rules;
    }

    @PostMapping
    public void createRule(@RequestBody TradingRule rule) {
        if (rule.getSymbol() == null || rule.getAction() == null || rule.getTriggerPrice() <= 0) {
            throw new IllegalArgumentException("Invalid rule: Symbol, Action must not be null and Trigger Price must be > 0");
        }
        rules.add(rule);
    }

    @PostMapping("/evaluate")
    public EvaluationResult evaluate(@RequestBody Map<String, Object> request) {
        String symbol = request.get("symbol").toString();
        double price = Double.parseDouble(request.get("price").toString());

        for (TradingRule rule : rules) {
            if (rule.getSymbol() != null && rule.getSymbol().equalsIgnoreCase(symbol)) {
                boolean match = RuleEngine.evaluateRule(rule, price);
                if (match) {
                    return new EvaluationResult(
                            symbol,
                            price,
                            rule.getTriggerPrice(),
                            rule.getAction(),
                            true
                    );
                }
            }
        }
        return new EvaluationResult(
                symbol,
                price,
                0,
                "NO_ACTION",
                false
        );
    }
}