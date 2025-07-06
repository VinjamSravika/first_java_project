package com.example.ruleengineservice.integration;
   import com.example.ruleengineservice.rules.TradingRule;
   import com.example.ruleengineservice.engine.RuleEngine;
   import org.json.JSONObject;
   import java.util.ArrayList;
   import java.util.List;

public class TestAPI {
    public static void main(String[] args) {
        List<TradingRule> rules = new ArrayList<>();

        // Add different stock trading rules
        rules.add(new TradingRule("AAPL", 210.0, "BUY"));
        rules.add(new TradingRule("MSFT", 600.0, "BUY"));
        rules.add(new TradingRule("AAPL", 220.0, "SELL")); // Apple sell if > 220

        for (TradingRule rule : rules) {
            String response = TwelveDataAPIClient.getStockQuote(rule.getSymbol());
            JSONObject json = new JSONObject(response);
            String name = json.getString("name");
            String priceStr = json.getString("close");
            double price = Double.parseDouble(priceStr);

            System.out.println("\n" + name + " Stock Price: $" + priceStr);
            RuleEngine.evaluateRule(rule, price);
        }
    }
}

