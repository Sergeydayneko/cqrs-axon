package ru.dayneko.handler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.stereotype.Component;
import ru.dayneko.event.ApprovedCreditMonetEvent;
import ru.dayneko.event.CreateAccountEvent;
import ru.dayneko.event.DebitMoneyEvent;
import ru.dayneko.model.entity.AccountEntity;
import ru.dayneko.repository.AccountRepository;
import ru.dayneko.utils.Status;

import javax.validation.constraints.NotNull;

@Component
@ProcessingGroup("accountProcessingGroup")
public class AccountProcessingGroup {

    private final @NotNull AccountRepository accountRepository;

    public AccountProcessingGroup(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventSourcingHandler
    protected void on(CreateAccountEvent createAccountEvent){
        accountRepository.save(new AccountEntity(
                createAccountEvent.getAccountBalance(),
                createAccountEvent.getCurrency(),
                Status.CREATED
        ));
    }

    @EventSourcingHandler
    protected void on(ApprovedCreditMonetEvent approvedCreditMonetEvent) {
        accountRepository.findById(approvedCreditMonetEvent.getId())
                .map(acc -> new AccountEntity(acc.getAccountBalance() - approvedCreditMonetEvent.getCreditAmount(), approvedCreditMonetEvent.getCurrency()))
                .ifPresentOrElse(accountRepository::save, System.err::println);
    }

    @EventSourcingHandler
    protected void on(DebitMoneyEvent debitMoneyEvent) {
        accountRepository.findById(debitMoneyEvent.getId())
                .map(acc -> new AccountEntity(acc.getAccountBalance() + debitMoneyEvent.getDebitAmount(), debitMoneyEvent.getCurrency()))
                .ifPresentOrElse(accountRepository::save, System.err::println);
    }
}
