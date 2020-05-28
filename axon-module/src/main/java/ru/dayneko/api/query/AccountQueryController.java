package ru.dayneko.api.query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dayneko.model.dto.AccountDTO;
import ru.dayneko.service.AccountService;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/account/query")
public class AccountQueryController {

    private final @NotNull AccountService accountService;

    public AccountQueryController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/all")
    public CompletableFuture<List<AccountDTO>> findAllAccounts() {
        return accountService.findAllAccountEntities();
    }

    @GetMapping("/one")
    public CompletableFuture<AccountDTO> getAccountById(@RequestParam(name = "id") @NotNull String id) {
        return accountService.findAccountById(id);
    }
}
