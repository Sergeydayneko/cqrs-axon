package ru.dayneko.event;

import javax.validation.constraints.NotNull;

/**
 * Event for confirmation of creation
 */
public class CreateAccountEvent extends Event<String> {

    private final double accountBalance;

    @NotNull
    private final String currency;

    public CreateAccountEvent(String id, double accountBalance, String currency) {
        super(id);
        this.accountBalance = accountBalance;
        this.currency = currency;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getCurrency() {
        return currency;
    }
}
