package ru.dayneko.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import ru.dayneko.comand.CreateAccountCommand;
import ru.dayneko.event.AccountCreatedEvent;

import javax.validation.constraints.NotNull;

/**
 * Aggregate of account with logic of
 * command and events handling
 */
@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String id;

    private double accountBalance;

    private String currency;

    private String status;

    /*
       First command of account has to be creating account.
       So that it is placed into constructor.
     */
    @CommandHandler
    public AccountAggregate(@NotNull CreateAccountCommand createAccountCommand) {
        AggregateLifecycle.apply(
                new AccountCreatedEvent(
                        createAccountCommand.getId()
                        , createAccountCommand.getAccountBalance()
                        , createAccountCommand.getCurrency()
                )
        );
    }

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent){
        this.id = accountCreatedEvent.getId();
        this.accountBalance = accountCreatedEvent.getAccountBalance();
        this.currency = accountCreatedEvent.getCurrency();
        this.status = "created";
    }

    public String getId() {
        return id;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }
}
