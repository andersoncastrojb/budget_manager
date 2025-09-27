package co.com.soft.service;

import co.com.soft.usecase.LoanUseCase;
import co.com.soft.model.Loan;
import co.com.soft.adapters.LoanAdapter;
import co.com.soft.entity.LoanEntity;
import co.com.soft.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class LoanService implements LoanUseCase {
    private final LoanRepository loanRepository;
    private final LoanAdapter loanAdapter;

    @Autowired
    public LoanService(LoanRepository loanRepository, LoanAdapter loanAdapter) {
        this.loanRepository = loanRepository;
        this.loanAdapter = loanAdapter;
    }

    @Override
    public Mono<Loan> createLoan(Loan loan) {
        LoanEntity entity = loanAdapter.toEntity(loan);
    return loanRepository.save(entity)
        .map(entityResult -> loanAdapter.toModel(entityResult));
    }

    @Override
    public Mono<Loan> updateLoan(Long id, Loan loan) {
        return loanRepository.findById(id)
                .flatMap(existing -> {
                    LoanEntity updated = loanAdapter.toEntity(loan);
                    updated.setId(id);
                    return loanRepository.save(updated);
                })
                .map(entityResult -> loanAdapter.toModel(entityResult));
    }

    @Override
    public Mono<Void> deleteLoan(Long id) {
        return loanRepository.deleteById(id);
    }

    @Override
    public Mono<Loan> getLoanById(Long id) {
    return loanRepository.findById(id)
        .map(entityResult -> loanAdapter.toModel(entityResult));
    }

    @Override
    public Flux<Loan> getAllLoans() {
    return loanRepository.findAll()
        .map(entityResult -> loanAdapter.toModel(entityResult));
    }
}
