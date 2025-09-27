package co.com.soft.service;

import co.com.soft.usecase.FixedExpenseUseCase;
import co.com.soft.model.FixedExpense;
import co.com.soft.adapters.FixedExpenseAdapter;
import co.com.soft.entity.FixedExpenseEntity;
import co.com.soft.repository.FixedExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class FixedExpenseService implements FixedExpenseUseCase {
    private final FixedExpenseRepository fixedExpenseRepository;
    private final FixedExpenseAdapter fixedExpenseAdapter;

    @Autowired
    public FixedExpenseService(FixedExpenseRepository fixedExpenseRepository, FixedExpenseAdapter fixedExpenseAdapter) {
        this.fixedExpenseRepository = fixedExpenseRepository;
        this.fixedExpenseAdapter = fixedExpenseAdapter;
    }

    @Override
    public Mono<FixedExpense> createFixedExpense(FixedExpense fixedExpense) {
        FixedExpenseEntity entity = fixedExpenseAdapter.toEntity(fixedExpense);
    return fixedExpenseRepository.save(entity)
        .map(entityResult -> fixedExpenseAdapter.toModel(entityResult));
    }

    @Override
    public Mono<FixedExpense> updateFixedExpense(Long id, FixedExpense fixedExpense) {
        return fixedExpenseRepository.findById(id)
                .flatMap(existing -> {
                    FixedExpenseEntity updated = fixedExpenseAdapter.toEntity(fixedExpense);
                    updated.setId(id);
                    return fixedExpenseRepository.save(updated);
                })
                .map(entityResult -> fixedExpenseAdapter.toModel(entityResult));
    }

    @Override
    public Mono<Void> deleteFixedExpense(Long id) {
        return fixedExpenseRepository.deleteById(id);
    }

    @Override
    public Mono<FixedExpense> getFixedExpenseById(Long id) {
    return fixedExpenseRepository.findById(id)
        .map(entityResult -> fixedExpenseAdapter.toModel(entityResult));
    }

    @Override
    public Flux<FixedExpense> getAllFixedExpenses() {
    return fixedExpenseRepository.findAll()
        .map(entityResult -> fixedExpenseAdapter.toModel(entityResult));
    }
}
