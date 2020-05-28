package ru.dayneko.model.dto;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CreateAccountModelDTO {
    private final String id;

    private final double balance;

    @NotNull
    private final String currency;

    public CreateAccountModelDTO(double balance, String currency) {
        if (balance < 0) throw new IllegalStateException("You cannot create account with negative balance");

        this.balance = balance;
        this.currency = currency;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }
}
