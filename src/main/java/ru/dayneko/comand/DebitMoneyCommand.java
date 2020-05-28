package ru.dayneko.comand;

import javax.validation.constraints.NotNull;

/**
 * Debit money to credit carrd
 */
public class DebitMoneyCommand extends Command<String> {

    private final double debitAmount;

    @NotNull
    private final String currency;

    public DebitMoneyCommand(@NotNull String id, double debitAmount, @NotNull String currency) {
        super(id);
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
