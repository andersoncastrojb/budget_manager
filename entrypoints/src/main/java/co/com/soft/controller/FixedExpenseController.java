package co.com.soft.controller;

import co.com.soft.model.FixedExpense;
import co.com.soft.usecase.FixedExpenseUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fixed-expenses")
public class FixedExpenseController {
    private final FixedExpenseUseCase fixedExpenseUseCase;

    @Autowired
    public FixedExpenseController(FixedExpenseUseCase fixedExpenseUseCase) {
        this.fixedExpenseUseCase = fixedExpenseUseCase;
    }

    @PostMapping
    public Mono<FixedExpense> createFixedExpense(@RequestBody FixedExpense fixedExpense) {
        return fixedExpenseUseCase.createFixedExpense(fixedExpense);
    }

    @PutMapping("/{id}")
    public Mono<FixedExpense> updateFixedExpense(@PathVariable("id") Long id, @RequestBody FixedExpense fixedExpense) {
        return fixedExpenseUseCase.updateFixedExpense(id, fixedExpense);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteFixedExpense(@PathVariable("id") Long id) {
        return fixedExpenseUseCase.deleteFixedExpense(id);
    }

    @GetMapping("/{id}")
    public Mono<FixedExpense> getFixedExpenseById(@PathVariable("id") Long id) {
        return fixedExpenseUseCase.getFixedExpenseById(id);
    }

    @GetMapping
    public Flux<FixedExpense> getAllFixedExpenses() {
        return fixedExpenseUseCase.getAllFixedExpenses();
    }
}
