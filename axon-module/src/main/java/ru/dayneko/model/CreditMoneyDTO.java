package ru.dayneko.model;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * DTO for credit money from account
 */
public class CreditMoneyDTO {
    private final String id;

    private final double creditAmount;

    @NotNull
    private final String currency;

    public CreditMoneyDTO(double creditAmount, @NotNull String currency) {
        this.creditAmount = creditAmount;
        this.currency = currency;
        this.id = UUID.randomUUID().toString();
    }

    public double getDebitAmount() {
        return creditAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getId() {
        return id;
    }
}
