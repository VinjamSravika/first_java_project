package com.example.ruleengineservice.controller;
import com.example.ruleengineservice.model.EvaluationResult;
import com.example.ruleengineservice.model.Rule;
import com.example.ruleengineservice.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private RuleService ruleService;

    @GetMapping("/evaluate")
    public List<EvaluationResult> evaluateAll() {
        List<EvaluationResult> results = new ArrayList<>();
        List<Rule> rules = ruleService.getAllRules();

        for (Rule rule : rules) {
            double currentPrice = fetchPrice(rule.getStockSymbol());

            boolean isTriggered = false;
            if ("BUY".equalsIgnoreCase(rule.getAction())) {
                isTriggered = currentPrice < rule.getTargetPrice();
            } else if ("SELL".equalsIgnoreCase(rule.getAction())) {
                isTriggered = currentPrice > rule.getTargetPrice();
            }

            EvaluationResult result = new EvaluationResult(
                    rule.getStockSymbol(),
                    currentPrice,
                    rule.getTargetPrice(),
                    rule.getAction(),
                    isTriggered
            );

            results.add(result);
        }

        return results;
    }

    private double fetchPrice(String symbol) {
        return switch (symbol.toUpperCase()) {
            case "AAPL" -> 213.55;
            case "MSFT" -> 498.84;
            case "TCS" -> 4050.25;
            case "INFY" -> 1550.75;
            case "RELIANCE" -> 2800.10;
            default -> 0.0;
        };
    }
}

