package co.com.soft.controller;

import co.com.soft.dto.IncomeCreateDTO;
import co.com.soft.dto.IncomeDTO;
import co.com.soft.dto.IncomeUpdateDTO;
import co.com.soft.mapper.IncomeMapper;
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
    public Mono<IncomeDTO> createIncome(@RequestBody IncomeCreateDTO createDto) {
        Income model = IncomeMapper.toModelFromCreate(createDto);
        return incomeUseCase.createIncome(model).map(IncomeMapper::toDTO);
    }

    @PutMapping
    public Mono<IncomeDTO> updateIncome(@RequestBody IncomeUpdateDTO updateDto) {
        Income model = IncomeMapper.toModelFromUpdate(updateDto);
        Long id = model.getId();
        return incomeUseCase.updateIncome(id, model).map(IncomeMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteIncome(@PathVariable("id") Long id) {
        return incomeUseCase.deleteIncome(id);
    }

    @GetMapping("/{id}")
    public Mono<IncomeDTO> getIncomeById(@PathVariable("id") Long id) {
        return incomeUseCase.getIncomeById(id).map(IncomeMapper::toDTO);
    }

    @GetMapping
    public Flux<IncomeDTO> getAllIncomes() {
        return incomeUseCase.getAllIncomes().map(IncomeMapper::toDTO);
    }
}
