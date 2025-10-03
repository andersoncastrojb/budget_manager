package co.com.soft.controller;

import co.com.soft.dto.MonthlyBalanceCreateDTO;
import co.com.soft.dto.MonthlyBalanceDTO;
import co.com.soft.dto.MonthlyBalanceUpdateDTO;
import co.com.soft.mapper.MonthlyBalanceMapper;
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
    public Mono<MonthlyBalanceDTO> createMonthlyBalance(@RequestBody MonthlyBalanceCreateDTO createDto) {
        MonthlyBalance model = MonthlyBalanceMapper.toModelFromCreate(createDto);
        return monthlyBalanceUseCase.createMonthlyBalance(model).map(MonthlyBalanceMapper::toDTO);
    }

    @PutMapping
    public Mono<MonthlyBalanceDTO> updateMonthlyBalance(@RequestBody MonthlyBalanceUpdateDTO updateDto) {
        MonthlyBalance model = MonthlyBalanceMapper.toModelFromUpdate(updateDto);
        Long id = model.getId();
        return monthlyBalanceUseCase.updateMonthlyBalance(id, model).map(MonthlyBalanceMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteMonthlyBalance(@PathVariable("id") Long id) {
        return monthlyBalanceUseCase.deleteMonthlyBalance(id);
    }

    @GetMapping("/{id}")
    public Mono<MonthlyBalanceDTO> getMonthlyBalanceById(@PathVariable("id") Long id) {
        return monthlyBalanceUseCase.getMonthlyBalanceById(id).map(MonthlyBalanceMapper::toDTO);
    }

    @GetMapping
    public Flux<MonthlyBalanceDTO> getAllMonthlyBalances() {
        return monthlyBalanceUseCase.getAllMonthlyBalances().map(MonthlyBalanceMapper::toDTO);
    }
}
