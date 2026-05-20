package co.com.soft.service;

import co.com.soft.usecase.AccountUseCase;
import co.com.soft.model.Account;
import co.com.soft.adapters.AccountAdapter;
import co.com.soft.entity.AccountEntity;
import co.com.soft.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class AccountService implements AccountUseCase {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Mono<Account> createAccount(Account account) {
        AccountEntity entity = AccountAdapter.toEntity(account);
        return accountRepository.save(entity)
                .map(AccountAdapter::toModel);
    }

    @Override
    public Mono<Account> updateAccount(Long id, Account account) {
        return accountRepository.findById(id)
                .flatMap(existing -> {
                    AccountEntity updated = AccountAdapter.toEntity(account);
                    updated.setId(id);
                    return accountRepository.save(updated);
                })
                .map(AccountAdapter::toModel);
    }

    @Override
    public Mono<Void> deleteAccount(Long id) {
        return accountRepository.deleteById(id);
    }

    @Override
    public Mono<Account> getAccountById(Long id) {
        return accountRepository.findById(id)
                .map(AccountAdapter::toModel);
    }

    @Override
    public Flux<Account> getAllAccounts() {
        return accountRepository.findAll()
                .map(AccountAdapter::toModel);
    }

    @Override
    public Flux<Account> getAccountsByUserId(Long userId) {
        return accountRepository.findAllByUserId(userId)
                .map(AccountAdapter::toModel);
    }
}
