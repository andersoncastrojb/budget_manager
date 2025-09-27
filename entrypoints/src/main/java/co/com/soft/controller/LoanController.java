package co.com.soft.controller;

import co.com.soft.model.Loan;
import co.com.soft.usecase.LoanUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/loans")
public class LoanController {
    private final LoanUseCase loanUseCase;

    @Autowired
    public LoanController(LoanUseCase loanUseCase) {
        this.loanUseCase = loanUseCase;
    }

    @PostMapping
    public Mono<Loan> createLoan(@RequestBody Loan loan) {
        return loanUseCase.createLoan(loan);
    }

    @PutMapping("/{id}")
    public Mono<Loan> updateLoan(@PathVariable("id") Long id, @RequestBody Loan loan) {
        return loanUseCase.updateLoan(id, loan);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteLoan(@PathVariable("id") Long id) {
        return loanUseCase.deleteLoan(id);
    }

    @GetMapping("/{id}")
    public Mono<Loan> getLoanById(@PathVariable("id") Long id) {
        return loanUseCase.getLoanById(id);
    }

    @GetMapping
    public Flux<Loan> getAllLoans() {
        return loanUseCase.getAllLoans();
    }
}
