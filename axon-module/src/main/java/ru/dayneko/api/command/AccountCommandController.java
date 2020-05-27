package ru.dayneko.api.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dayneko.comand.ActivateAccountCommand;
import ru.dayneko.comand.DebitMoneyCommand;
import ru.dayneko.model.CreateAccountModelDTO;
import ru.dayneko.model.CreditMoneyDTO;
import ru.dayneko.model.DebitMoneyDTO;
import ru.dayneko.utils.Status;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;


@RestController
@RequestMapping("/account")
public class AccountCommandController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @NotNull
    private final CommandGateway commandGateway;

    public AccountCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(@RequestBody @Valid CreateAccountModelDTO createAccountModelDTO) {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->
                commandGateway.sendAndWait(new ActivateAccountCommand(createAccountModelDTO.getId(), Status.CREATED)
        ));

        return completableFuture
                .thenApply(Function.identity())
                .exceptionally((e) -> {
                    log.error("Error occurred while trying to create {}", createAccountModelDTO);
                    throw new RuntimeException(e);
                });
    }

    @PostMapping("/debit")
    public CompletableFuture<String> debit(@RequestBody @Valid DebitMoneyDTO debitMoneyDTO) {

        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() ->
                        commandGateway.sendAndWait(
                                new DebitMoneyCommand(debitMoneyDTO.getId()
                                        , debitMoneyDTO.getDebitAmount()
                                        , debitMoneyDTO.getCurrency()
                                )
                ));

        return completableFuture
                .thenApply(Function.identity())
                .exceptionally((e) -> {
                    log.error("Error occurred while trying to debit money {}", debitMoneyDTO);
                    throw new RuntimeException(e);
                });
    }

    @PostMapping
    public CompletableFuture<String> credit(@RequestBody @Valid CreditMoneyDTO creditMoneyDTO) {
        CompletableFuture<String> completableFuture =
                CompletableFuture.supplyAsync(() ->
                        commandGateway.sendAndWait(
                                new DebitMoneyCommand(creditMoneyDTO.getId()
                                        , creditMoneyDTO.getDebitAmount()
                                        , creditMoneyDTO.getCurrency()
                                )
                        ));

        return completableFuture
                .thenApply(Function.identity())
                .exceptionally((e) -> {
                    log.error("Error occurred while trying to credit money {}", creditMoneyDTO);
                    throw new RuntimeException(e);
                });
    }
}
