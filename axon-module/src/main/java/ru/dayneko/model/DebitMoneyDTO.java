package ru.dayneko.model;

import javax.validation.constraints.NotNull;

public class DebitMoneyDTO {
    private final double debitAmount;

    @NotNull
    private final String currency;

    public DebitMoneyDTO(double debitAmount, @NotNull String currency) {
        this.debitAmount = debitAmount;
        this.currency = currency;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public String getCurrency() {
        return currency;
    }
}
