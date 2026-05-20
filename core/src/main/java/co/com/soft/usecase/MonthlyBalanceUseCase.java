package co.com.soft.usecase;

import co.com.soft.model.MonthlyBalance;
import reactor.core.publisher.Mono;

public interface MonthlyBalanceUseCase {
    Mono<MonthlyBalance> processMonthlyBalance(Long userId, Integer month, Integer year);
}
