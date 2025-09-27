 package co.com.soft.usecase;

import co.com.soft.model.Loan;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface LoanUseCase {
    Mono<Loan> createLoan(Loan loan);
    Mono<Loan> updateLoan(Long id, Loan loan);
    Mono<Void> deleteLoan(Long id);
    Mono<Loan> getLoanById(Long id);
    Flux<Loan> getAllLoans();
}
