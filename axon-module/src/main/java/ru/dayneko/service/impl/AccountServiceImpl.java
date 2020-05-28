package ru.dayneko.service.impl;

import org.springframework.stereotype.Service;
import ru.dayneko.model.dto.AccountDTO;
import ru.dayneko.repository.AccountRepository;
import ru.dayneko.service.AccountService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final @NotNull AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CompletableFuture<List<AccountDTO>> findAllAccountEntities() {
        return CompletableFuture.supplyAsync(
                () -> accountRepository.findAll()
                        .stream()
                        .map(AccountDTO::new)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public CompletableFuture<AccountDTO> findAccountById(String id) {
        return CompletableFuture.supplyAsync(
                () -> accountRepository.findById(id)
                        .map(AccountDTO::new)
                        .orElseThrow(IllegalAccessError::new)
        );
    }
}
