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

    @Autowired
    public FixedExpenseService(FixedExpenseRepository fixedExpenseRepository) {
        this.fixedExpenseRepository = fixedExpenseRepository;
    }

    @Override
    public Mono<FixedExpense> createFixedExpense(FixedExpense fixedExpense) {
        FixedExpenseEntity entity = FixedExpenseAdapter.toEntity(fixedExpense);
        return fixedExpenseRepository.save(entity)
                .map(FixedExpenseAdapter::toModel);
    }

    @Override
    public Mono<FixedExpense> updateFixedExpense(Long id, FixedExpense fixedExpense) {
        return fixedExpenseRepository.findById(id)
                .flatMap(existing -> {
                    FixedExpenseEntity updated = FixedExpenseAdapter.toEntity(fixedExpense);
                    updated.setId(id);
                    return fixedExpenseRepository.save(updated);
                })
                .map(FixedExpenseAdapter::toModel);
    }

    @Override
    public Mono<Void> deleteFixedExpense(Long id) {
        return fixedExpenseRepository.deleteById(id);
    }

    @Override
    public Mono<FixedExpense> getFixedExpenseById(Long id) {
        return fixedExpenseRepository.findById(id)
                .map(FixedExpenseAdapter::toModel);
    }

    @Override
    public Flux<FixedExpense> getAllFixedExpenses() {
        return fixedExpenseRepository.findAll()
                .map(FixedExpenseAdapter::toModel);
    }

    @Override
    public Flux<FixedExpense> getFixedExpensesByUserId(Long userId) {
        return fixedExpenseRepository.findAllByUserId(userId)
                .map(FixedExpenseAdapter::toModel);
    }
}
