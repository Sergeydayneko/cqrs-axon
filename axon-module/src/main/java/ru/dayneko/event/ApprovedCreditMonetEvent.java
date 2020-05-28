package ru.dayneko.event;

import javax.validation.constraints.NotNull;

/**
 * Event of crediting money after restrictions check
 */
public class ApprovedCreditMonetEvent extends Event<String> {

    private final double creditAmount;

    @NotNull
    private final String currency;

    public ApprovedCreditMonetEvent(String id, double creditAmount, String currency) {
        super(id);
        this.creditAmount = creditAmount;
        this.currency = currency;
    }

    public ApprovedCreditMonetEvent(CreditMoneyEvent creditMoneyEvent) {
        super(creditMoneyEvent.getId());
        this.creditAmount = creditMoneyEvent.getCreditAmount();
        this.currency = creditMoneyEvent.getCurrency();
    }

    public double getCreditAmount() {
        return creditAmount;
    }

    public String getCurrency() {
        return currency;
    }
}
