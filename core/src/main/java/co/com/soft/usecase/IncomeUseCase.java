package co.com.soft.usecase;

import co.com.soft.model.Income;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface IncomeUseCase {
    Mono<Income> createIncome(Income income);
    Mono<Income> updateIncome(Long id, Income income);
    Mono<Void> deleteIncome(Long id);
    Mono<Income> getIncomeById(Long id);
    Flux<Income> getAllIncomes();
}
