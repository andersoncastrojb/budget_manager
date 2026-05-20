package co.com.soft.controller;

import co.com.soft.dto.FixedExpenseCreateDTO;
import co.com.soft.dto.FixedExpenseDTO;
import co.com.soft.dto.FixedExpenseUpdateDTO;
import co.com.soft.mapper.FixedExpenseMapper;
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
    public Mono<FixedExpenseDTO> createFixedExpense(@RequestBody FixedExpenseCreateDTO createDto) {
        FixedExpense model = FixedExpenseMapper.toModelFromCreate(createDto);
        return fixedExpenseUseCase.createFixedExpense(model).map(FixedExpenseMapper::toDTO);
    }

    @PutMapping
    public Mono<FixedExpenseDTO> updateFixedExpense(@RequestBody FixedExpenseUpdateDTO updateDto) {
        FixedExpense model = FixedExpenseMapper.toModelFromUpdate(updateDto);
        Long id = model.getId();
        return fixedExpenseUseCase.updateFixedExpense(id, model).map(FixedExpenseMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteFixedExpense(@PathVariable("id") Long id) {
        return fixedExpenseUseCase.deleteFixedExpense(id);
    }

    @GetMapping("/{id}")
    public Mono<FixedExpenseDTO> getFixedExpenseById(@PathVariable("id") Long id) {
        return fixedExpenseUseCase.getFixedExpenseById(id).map(FixedExpenseMapper::toDTO);
    }

    @GetMapping
    public Flux<FixedExpenseDTO> getAllFixedExpenses() {
        return fixedExpenseUseCase.getAllFixedExpenses().map(FixedExpenseMapper::toDTO);
    }
}
