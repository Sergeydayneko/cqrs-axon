package ru.dayneko.comand;

import javax.validation.constraints.NotNull;

public class CreateAccountCommand extends Command<String> {

    private final double accountBalance;

    @NotNull
    private final String currency;

    public CreateAccountCommand(String id, double accountBalance, String currency) {
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
