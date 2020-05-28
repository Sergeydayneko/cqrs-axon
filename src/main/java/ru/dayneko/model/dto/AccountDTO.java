package ru.dayneko.model.dto;

import ru.dayneko.model.entity.AccountEntity;
import ru.dayneko.utils.Status;

import javax.validation.constraints.NotNull;

/**
 * Transfer object for account entity
 */
public class AccountDTO {
    private String id;

    private final double accountBalance;

    @NotNull
    private final String currency;

    @NotNull
    private final Status status;

    public AccountDTO(double accountBalance, String currency, Status status) {
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.status = status;
    }

    public AccountDTO(AccountEntity accountEntity) {
        this.accountBalance = accountEntity.getAccountBalance();
        this.currency = accountEntity.getCurrency();
        this.status = accountEntity.getStatus();
    }
}
