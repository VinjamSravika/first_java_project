package com.example.ruleengineservice.model;

public class EvaluationResult {
    private String action;
    private double currentPrice;
    private double triggerPrice;
    private String symbol;
    private boolean matched;

    public EvaluationResult(String symbol, double currentPrice, double triggerPrice, String action, boolean matched) {
        this.symbol = symbol;
        this.currentPrice = currentPrice;
        this.triggerPrice = triggerPrice;
        this.action = action;
        this.matched = matched;
    }


    public EvaluationResult(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getTriggerPrice() {
        return triggerPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setTriggerPrice(double triggerPrice) {
        this.triggerPrice = triggerPrice;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }
}
