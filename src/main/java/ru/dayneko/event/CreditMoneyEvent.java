package ru.dayneko.event;

import javax.validation.constraints.NotNull;

/**
 * Credit money from account event
 */
public class CreditMoneyEvent extends Event<String> {

    private final double creditAmount;

    @NotNull
    private final String currency;

    public CreditMoneyEvent(String id, double creditAmount, String currency) {
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
