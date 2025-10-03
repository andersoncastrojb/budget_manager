package co.com.soft.controller;

import co.com.soft.dto.MonthlyBalanceDTO;
import co.com.soft.mapper.MonthlyBalanceMapper;
import co.com.soft.usecase.MonthlyBalanceUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/monthly-balances")
public class MonthlyBalanceController {
    private final MonthlyBalanceUseCase monthlyBalanceUseCase;

    @Autowired
    public MonthlyBalanceController(MonthlyBalanceUseCase monthlyBalanceUseCase) {
        this.monthlyBalanceUseCase = monthlyBalanceUseCase;
    }

    @GetMapping("/{id}")
    public Mono<MonthlyBalanceDTO> getMonthlyBalanceById(@PathVariable("id") Long id) {
        return monthlyBalanceUseCase.getMonthlyBalanceById(id).map(MonthlyBalanceMapper::toDTO);
    }
}
