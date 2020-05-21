package ru.dayneko.api.command;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.dayneko.comand.ActivateAccountCommand;
import ru.dayneko.model.CreateAccountModelDTO;
import ru.dayneko.utils.Status;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;
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

    @PostMapping
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
}
