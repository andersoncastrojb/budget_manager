package co.com.soft.service;

import co.com.soft.usecase.IncomeUseCase;
import co.com.soft.model.Income;
import co.com.soft.adapters.IncomeAdapter;
import co.com.soft.entity.IncomeEntity;
import co.com.soft.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class IncomeService implements IncomeUseCase {
    private final IncomeRepository incomeRepository;
    private final IncomeAdapter incomeAdapter;

    @Autowired
    public IncomeService(IncomeRepository incomeRepository, IncomeAdapter incomeAdapter) {
        this.incomeRepository = incomeRepository;
        this.incomeAdapter = incomeAdapter;
    }

    @Override
    public Mono<Income> createIncome(Income income) {
        IncomeEntity entity = incomeAdapter.toEntity(income);
    return incomeRepository.save(entity)
        .map(entityResult -> incomeAdapter.toModel(entityResult));
    }

    @Override
    public Mono<Income> updateIncome(Long id, Income income) {
        return incomeRepository.findById(id)
                .flatMap(existing -> {
                    IncomeEntity updated = incomeAdapter.toEntity(income);
                    updated.setId(id);
                    return incomeRepository.save(updated);
                })
                .map(entityResult -> incomeAdapter.toModel(entityResult));
    }

    @Override
    public Mono<Void> deleteIncome(Long id) {
        return incomeRepository.deleteById(id);
    }

    @Override
    public Mono<Income> getIncomeById(Long id) {
    return incomeRepository.findById(id)
        .map(entityResult -> incomeAdapter.toModel(entityResult));
    }

    @Override
    public Flux<Income> getAllIncomes() {
    return incomeRepository.findAll()
        .map(entityResult -> incomeAdapter.toModel(entityResult));
    }
}
