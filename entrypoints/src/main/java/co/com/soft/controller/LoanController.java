package co.com.soft.controller;

import co.com.soft.dto.LoanCreateDTO;
import co.com.soft.dto.LoanDTO;
import co.com.soft.dto.LoanUpdateDTO;
import co.com.soft.mapper.LoanMapper;
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
    public Mono<LoanDTO> createLoan(@RequestBody LoanCreateDTO createDto) {
        Loan model = LoanMapper.toModelFromCreate(createDto);
        return loanUseCase.createLoan(model).map(LoanMapper::toDTO);
    }

    @PutMapping
    public Mono<LoanDTO> updateLoan(@RequestBody LoanUpdateDTO updateDto) {
        Loan model = LoanMapper.toModelFromUpdate(updateDto);
        Long id = model.getId();
        return loanUseCase.updateLoan(id, model).map(LoanMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteLoan(@PathVariable("id") Long id) {
        return loanUseCase.deleteLoan(id);
    }

    @GetMapping("/{id}")
    public Mono<LoanDTO> getLoanById(@PathVariable("id") Long id) {
        return loanUseCase.getLoanById(id).map(LoanMapper::toDTO);
    }

    @GetMapping
    public Flux<LoanDTO> getAllLoans() {
        return loanUseCase.getAllLoans().map(LoanMapper::toDTO);
    }
}
