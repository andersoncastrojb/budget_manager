package co.com.soft.controller;

import co.com.soft.model.Account;
import co.com.soft.usecase.AccountUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountUseCase accountUseCase;

    @Autowired
    public AccountController(AccountUseCase accountUseCase) {
        this.accountUseCase = accountUseCase;
    }

    @PostMapping
    public Mono<Account> createAccount(@RequestBody Account account) {
        return accountUseCase.createAccount(account);
    }

    @PutMapping("/{id}")
    public Mono<Account> updateAccount(@PathVariable("id") Long id, @RequestBody Account account) {
        return accountUseCase.updateAccount(id, account);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAccount(@PathVariable("id") Long id) {
        return accountUseCase.deleteAccount(id);
    }

    @GetMapping("/{id}")
    public Mono<Account> getAccountById(@PathVariable("id") Long id) {
        return accountUseCase.getAccountById(id);
    }

    @GetMapping
    public Flux<Account> getAllAccounts() {
        return accountUseCase.getAllAccounts();
    }
}
