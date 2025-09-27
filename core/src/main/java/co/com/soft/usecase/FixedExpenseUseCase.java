package co.com.soft.usecase;

import co.com.soft.model.FixedExpense;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface FixedExpenseUseCase {
    Mono<FixedExpense> createFixedExpense(FixedExpense fixedExpense);
    Mono<FixedExpense> updateFixedExpense(Long id, FixedExpense fixedExpense);
    Mono<Void> deleteFixedExpense(Long id);
    Mono<FixedExpense> getFixedExpenseById(Long id);
    Flux<FixedExpense> getAllFixedExpenses();
}
