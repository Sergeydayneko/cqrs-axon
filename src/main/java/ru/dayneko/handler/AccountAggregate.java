package ru.dayneko.handler;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.commandhandling.model.AggregateLifecycle;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import ru.dayneko.comand.ActivateAccountCommand;
import ru.dayneko.comand.CreateAccountCommand;
import ru.dayneko.comand.CreditMoneyCommand;
import ru.dayneko.comand.DebitMoneyCommand;
import ru.dayneko.event.*;
import ru.dayneko.utils.Reason;
import ru.dayneko.utils.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static ru.dayneko.utils.Status.BLOCKED;
import static ru.dayneko.utils.Status.CREATED;

/**
 * Aggregate of account with logic of
 * command and events handling
 */
@Aggregate
public class AccountAggregate implements Serializable {

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
                new CreateAccountEvent(
                        createAccountCommand.getId()
                        , createAccountCommand.getAccountBalance()
                        , createAccountCommand.getCurrency()
                )
        );
    }

    @CommandHandler
    protected void on(CreditMoneyCommand creditMoneyCommand){
        AggregateLifecycle.apply(
                new CreditMoneyEvent(
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

    @CommandHandler
    protected void DebitMoneyCommand(DebitMoneyCommand debitMoneyCommand) {
        AggregateLifecycle.apply(
                new DebitMoneyEvent(
                        debitMoneyCommand.getId()
                        , debitMoneyCommand.getDebitAmount()
                        , debitMoneyCommand.getCurrency()
                )
        );
    }

    /**
     ************* Event Handlers*************
     */

    @EventSourcingHandler
    protected void on(CreateAccountEvent createAccountEvent){
        this.id = createAccountEvent.getId();
        this.accountBalance = createAccountEvent.getAccountBalance();
        this.currency = createAccountEvent.getCurrency();
        this.status =  Status.CREATED;
    }

    @EventSourcingHandler
    protected void on(CreditMoneyEvent creditMoneyEvent) {
        if (status == CREATED) {
            AggregateLifecycle.apply(new RejectCreditEvent(creditMoneyEvent.getId(), Reason.NOT_ACTIVATED));
        }

        if (status == BLOCKED) {
            AggregateLifecycle.apply(new RejectCreditEvent(creditMoneyEvent.getId(), Reason.BLOCKED));
        }

        if (accountBalance < 0 || (accountBalance - creditMoneyEvent.getCreditAmount()) < 0) {
            AggregateLifecycle.apply(new RejectCreditEvent(creditMoneyEvent.getId(), Reason.INSUFFICIENT_FUNDS));
        }

        AggregateLifecycle.apply(new ApprovedCreditMonetEvent(creditMoneyEvent));

        accountBalance += creditMoneyEvent.getCreditAmount();
    }

    @EventSourcingHandler
    protected void on(DebitMoneyEvent debitMoneyEvent) {
        double debitAmount = debitMoneyEvent.getDebitAmount();

        if (debitAmount > 0) {
            accountBalance += debitAmount;
        }
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
