package co.com.soft.controller;

import co.com.soft.model.Income;
import co.com.soft.usecase.IncomeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/incomes")
public class IncomeController {
    private final IncomeUseCase incomeUseCase;

    @Autowired
    public IncomeController(IncomeUseCase incomeUseCase) {
        this.incomeUseCase = incomeUseCase;
    }

    @PostMapping
    public Mono<Income> createIncome(@RequestBody Income income) {
        return incomeUseCase.createIncome(income);
    }

    @PutMapping("/{id}")
    public Mono<Income> updateIncome(@PathVariable("id") Long id, @RequestBody Income income) {
        return incomeUseCase.updateIncome(id, income);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteIncome(@PathVariable("id") Long id) {
        return incomeUseCase.deleteIncome(id);
    }

    @GetMapping("/{id}")
    public Mono<Income> getIncomeById(@PathVariable("id") Long id) {
        return incomeUseCase.getIncomeById(id);
    }

    @GetMapping
    public Flux<Income> getAllIncomes() {
        return incomeUseCase.getAllIncomes();
    }
}
