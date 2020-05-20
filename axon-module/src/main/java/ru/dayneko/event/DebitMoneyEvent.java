package ru.dayneko.event;

import javax.validation.constraints.NotNull;

/**
 * Event for debit money
 */
public class DebitMoneyEvent extends Event<String> {

    private final double debitAmount;

    @NotNull
    private final String currency;

    public DebitMoneyEvent(String id, double debitAmount, String currency) {
        super(id);
        this.debitAmount = debitAmount;
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public double getDebitAmount() {
        return debitAmount;
    }
}
