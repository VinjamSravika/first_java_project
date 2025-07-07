package com.example.ruleengineservice.service;

import com.example.ruleengineservice.repository.RuleRepository;
import com.example.ruleengineservice.model.Rule;
import com.example.ruleengineservice.model.EvaluationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.jexl3.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RuleService {

    @Autowired
    private RuleRepository ruleRepository;

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }

    public Optional<Rule> getRuleById(Long id) {
        return ruleRepository.findById(id);
    }

    public Rule createRule(Rule rule) {
        return ruleRepository.save(rule);
    }

    public void deleteRule(Long id) {
        ruleRepository.deleteById(id);
    }

    public Rule updateRule(Long id, Rule updatedRule) {
        Rule rule = ruleRepository.findById(id).orElseThrow();
        rule.setStockSymbol(updatedRule.getStockSymbol());
        rule.setTargetPrice(updatedRule.getTargetPrice());
        rule.setCondition(updatedRule.getCondition());
        rule.setAction(updatedRule.getAction());
        rule.setActive(updatedRule.isActive());
        return ruleRepository.save(rule);
    }

    public EvaluationResult evaluate(Map<String, Object> input) {
        List<Rule> rules = ruleRepository.findAll();

        Map<String, Object> flatInput = flattenJson(input);
        System.out.println("Flattened input: " + flatInput);

        for (Rule rule : rules) {
            System.out.println("Evaluating rule condition: " + rule.getCondition());
            boolean matched = evaluateCondition(rule.getCondition(), flatInput);
            System.out.println("Condition result: " + matched);
            if (matched) {
                System.out.println("Rule matched, returning action: " + rule.getAction());
                return new EvaluationResult(rule.getAction());
            }
        }

        System.out.println("No rules matched, returning default action: flag");
        return new EvaluationResult("flag");
    }

    private boolean evaluateCondition(String condition, Map<String, Object> variables) {
        try {
            JexlEngine jexl = new JexlBuilder().create();
            JexlExpression expression = jexl.createExpression(condition);
            JexlContext context = new MapContext(variables);
            Object result = expression.evaluate(context);
            if (result instanceof Boolean) {
                return (Boolean) result;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Map<String, Object> flattenJson(Map<String, Object> input) {
        Map<String, Object> result = new HashMap<>();
        flatten("", input, result);
        return result;
    }


    private void flatten(String prefix, Map<String, Object> map, Map<String, Object> result) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
            if (entry.getValue() instanceof Map) {
                flatten(key, (Map<String, Object>) entry.getValue(), result);
            } else {
                result.put(key, entry.getValue());
            }
        }
    }
}
