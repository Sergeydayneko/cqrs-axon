package ru.dayneko.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import ru.dayneko.comand.ActivateAccountCommand;
import ru.dayneko.comand.CreateAccountCommand;
import ru.dayneko.comand.CreditMoneyCommand;
import ru.dayneko.event.AccountCreatedEvent;
import ru.dayneko.event.ActivateAccountEvent;
import ru.dayneko.event.MoneyCreditedEvent;
import ru.dayneko.event.RejectCreditEvent;
import ru.dayneko.utils.Reason;
import ru.dayneko.utils.Status;

import javax.validation.constraints.NotNull;

import static ru.dayneko.utils.Status.BLOCKED;
import static ru.dayneko.utils.Status.CREATED;

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

    private Status status;

    /**
     ************* Command Handlers *************
     */

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

    @CommandHandler
    protected void on(CreditMoneyCommand creditMoneyCommand){
        AggregateLifecycle.apply(
                new MoneyCreditedEvent(
                        creditMoneyCommand.getId()
                        , creditMoneyCommand.getCreditAmount()
                        , creditMoneyCommand.getCurrency()
                )
        );
    }

    @CommandHandler
    protected void on(ActivateAccountCommand activateAccountCommand) {
        AggregateLifecycle.apply(
                new ActivateAccountEvent(activateAccountCommand.getId(), activateAccountCommand.getStatus())
        );
    }

    /**
     ************* Event Handlers*************
     */

    @EventSourcingHandler
    protected void on(AccountCreatedEvent accountCreatedEvent){
        this.id = accountCreatedEvent.getId();
        this.accountBalance = accountCreatedEvent.getAccountBalance();
        this.currency = accountCreatedEvent.getCurrency();
        this.status =  Status.CREATED;
    }



    @EventSourcingHandler
    protected void on(MoneyCreditedEvent moneyCreditedEvent) {
        if (status == CREATED) {
            AggregateLifecycle.apply(new RejectCreditEvent(moneyCreditedEvent.getId(), Reason.NOT_ACTIVATED));
        }

        if (status == BLOCKED) {
            AggregateLifecycle.apply(new RejectCreditEvent(moneyCreditedEvent.getId(), Reason.BLOCKED));
        }

        if (accountBalance < 0 || (accountBalance - moneyCreditedEvent.getCreditAmount()) < 0) {
            AggregateLifecycle.apply(new RejectCreditEvent(moneyCreditedEvent.getId(), Reason.INSUFFICIENT_FUNDS));
        }

        accountBalance += moneyCreditedEvent.getCreditAmount();
    }

    @EventSourcingHandler
    protected void on(RejectCreditEvent rejectCreditEvent) {
        // save in event store
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

    public Status getStatus() {
        return status;
    }
}
