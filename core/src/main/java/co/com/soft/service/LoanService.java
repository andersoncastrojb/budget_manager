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

    @Autowired
    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Mono<Loan> createLoan(Loan loan) {
        LoanEntity entity = LoanAdapter.toEntity(loan);
        return loanRepository.save(entity)
                .map(LoanAdapter::toModel);
    }

    @Override
    public Mono<Loan> updateLoan(Long id, Loan loan) {
        return loanRepository.findById(id)
                .flatMap(existing -> {
                    LoanEntity updated = LoanAdapter.toEntity(loan);
                    updated.setId(id);
                    return loanRepository.save(updated);
                })
                .map(LoanAdapter::toModel);
    }

    @Override
    public Mono<Void> deleteLoan(Long id) {
        return loanRepository.deleteById(id);
    }

    @Override
    public Mono<Loan> getLoanById(Long id) {
        return loanRepository.findById(id)
                .map(LoanAdapter::toModel);
    }

    @Override
    public Flux<Loan> getAllLoans() {
        return loanRepository.findAll()
                .map(LoanAdapter::toModel);
    }
}
