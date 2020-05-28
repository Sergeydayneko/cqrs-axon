package ru.dayneko.model.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * DTO for deposit money into an account
 */
public class DebitMoneyDTO {
    private final String id;

    private final double debitAmount;

    @NotNull
    private final String currency;

    public DebitMoneyDTO(double debitAmount, @NotNull String currency) {
        if (debitAmount < 0) throw new IllegalStateException("Money deposited to the account must be a positive number");

        this.debitAmount = debitAmount;
        this.currency = currency;
        this.id = UUID.randomUUID().toString();
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getId() {
        return id;
    }
}
