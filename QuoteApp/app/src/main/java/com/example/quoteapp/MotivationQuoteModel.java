package com.example.weatherapp;

public class MotivationQuoteModel {
    private String quote;

    public MotivationQuoteModel(String quote) {
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String toString() {
        return "Motivation quote of the day: " + quote ;
    }
}
