package co.com.soft.controller;

import co.com.soft.model.MonthlyBalance;
import co.com.soft.usecase.MonthlyBalanceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/monthly-balances")
public class MonthlyBalanceController {
    private final MonthlyBalanceUseCase monthlyBalanceUseCase;

    @Autowired
    public MonthlyBalanceController(MonthlyBalanceUseCase monthlyBalanceUseCase) {
        this.monthlyBalanceUseCase = monthlyBalanceUseCase;
    }

    @PostMapping
    public Mono<MonthlyBalance> createMonthlyBalance(@RequestBody MonthlyBalance monthlyBalance) {
        return monthlyBalanceUseCase.createMonthlyBalance(monthlyBalance);
    }

    @PutMapping("/{id}")
    public Mono<MonthlyBalance> updateMonthlyBalance(@PathVariable("id") Long id, @RequestBody MonthlyBalance monthlyBalance) {
        return monthlyBalanceUseCase.updateMonthlyBalance(id, monthlyBalance);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMonthlyBalance(@PathVariable("id") Long id) {
        return monthlyBalanceUseCase.deleteMonthlyBalance(id);
    }

    @GetMapping("/{id}")
    public Mono<MonthlyBalance> getMonthlyBalanceById(@PathVariable("id") Long id) {
        return monthlyBalanceUseCase.getMonthlyBalanceById(id);
    }

    @GetMapping
    public Flux<MonthlyBalance> getAllMonthlyBalances() {
        return monthlyBalanceUseCase.getAllMonthlyBalances();
    }
}
