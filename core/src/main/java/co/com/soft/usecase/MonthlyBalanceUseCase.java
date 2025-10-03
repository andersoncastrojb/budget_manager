package co.com.soft.usecase;

import co.com.soft.model.MonthlyBalance;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface MonthlyBalanceUseCase {
    Mono<MonthlyBalance> getMonthlyBalanceById(Long id);
}
