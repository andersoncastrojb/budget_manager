package co.com.soft.usecase;

import co.com.soft.model.MonthlyBalance;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface MonthlyBalanceUseCase {
    Mono<MonthlyBalance> createMonthlyBalance(MonthlyBalance monthlyBalance);
    Mono<MonthlyBalance> updateMonthlyBalance(Long id, MonthlyBalance monthlyBalance);
    Mono<Void> deleteMonthlyBalance(Long id);
    Mono<MonthlyBalance> getMonthlyBalanceById(Long id);
    Flux<MonthlyBalance> getAllMonthlyBalances();
}
