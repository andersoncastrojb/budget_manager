package co.com.soft.controller;

import co.com.soft.dto.AccountCreateDTO;
import co.com.soft.dto.AccountDTO;
import co.com.soft.dto.AccountUpdateDTO;
import co.com.soft.mapper.AccountMapper;
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
    public Mono<AccountDTO> createAccount(@RequestBody AccountCreateDTO createDto) {
        Account model = AccountMapper.toModelFromCreate(createDto);
        return accountUseCase.createAccount(model).map(AccountMapper::toDTO);
    }

    @PutMapping
    public Mono<AccountDTO> updateAccount(@RequestBody AccountUpdateDTO updateDto) {
        Account model = AccountMapper.toModelFromUpdate(updateDto);
        Long id = model.getId();
        return accountUseCase.updateAccount(id, model).map(AccountMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteAccount(@PathVariable("id") Long id) {
        return accountUseCase.deleteAccount(id);
    }

    @GetMapping("/{id}")
    public Mono<AccountDTO> getAccountById(@PathVariable("id") Long id) {
        return accountUseCase.getAccountById(id).map(AccountMapper::toDTO);
    }

    @GetMapping
    public Flux<AccountDTO> getAllAccounts() {
        return accountUseCase.getAllAccounts().map(AccountMapper::toDTO);
    }
}
