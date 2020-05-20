package ru.dayneko.event;

/**
 * Credit money from account event
 */
public class MoneyCreditedEvent extends Event<String> {

    private final double creditAmount;

    private final String currency;

    public MoneyCreditedEvent(String id, double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public String getCurrency() {
        return currency;
    }
}
