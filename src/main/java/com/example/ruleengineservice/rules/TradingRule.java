package com.example.ruleengineservice.rules;

public class TradingRule {
    private String symbol;
    private double triggerPrice;
    private String action;

    public TradingRule() {

    }
    public TradingRule(String symbol, double triggerPrice, String action) {
        this.symbol = symbol;
        this.triggerPrice = triggerPrice;
        this.action = action;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getTriggerPrice() {
        return triggerPrice;
    }

    public void setTriggerPrice(double triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
