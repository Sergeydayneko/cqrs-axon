package ru.dayneko.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.dayneko.utils.Status;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Account entity object
 */
@Document(collection = "account")
public class AccountEntity {
    @Id
    private String id;

    private final double accountBalance;

    @NotNull
    private final String currency;

    @NotNull
    private final Status status;

    public AccountEntity(double accountBalance, String currency, Status status) {
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.status = status;
    }

    public AccountEntity(double accountBalance, String currency) {
        this.accountBalance = accountBalance;
        this.currency = currency;
        this.status = Status.ACTIVATED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        return Objects.equals(this, that);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public String toString() {
        return Objects.toString(this);
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public Status getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }
}
