package ru.dayneko.service;

import ru.dayneko.model.dto.AccountDTO;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AccountService {

    CompletableFuture<List<AccountDTO>> findAllAccountEntities();

    CompletableFuture<AccountDTO> findAccountById(@NotNull String id);
}
