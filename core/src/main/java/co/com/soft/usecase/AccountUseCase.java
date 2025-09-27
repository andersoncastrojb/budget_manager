package co.com.soft.usecase;

import co.com.soft.model.Account;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface AccountUseCase {
    Mono<Account> createAccount(Account account);
    Mono<Account> updateAccount(Long id, Account account);
    Mono<Void> deleteAccount(Long id);
    Mono<Account> getAccountById(Long id);
    Flux<Account> getAllAccounts();
}
