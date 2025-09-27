package co.com.soft.service;

import co.com.soft.usecase.MonthlyBalanceUseCase;
import co.com.soft.model.MonthlyBalance;
import co.com.soft.adapters.MonthlyBalanceAdapter;
import co.com.soft.entity.MonthlyBalanceEntity;
import co.com.soft.repository.MonthlyBalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class MonthlyBalanceService implements MonthlyBalanceUseCase {
    private final MonthlyBalanceRepository monthlyBalanceRepository;
    private final MonthlyBalanceAdapter monthlyBalanceAdapter;

    @Autowired
    public MonthlyBalanceService(MonthlyBalanceRepository monthlyBalanceRepository, MonthlyBalanceAdapter monthlyBalanceAdapter) {
        this.monthlyBalanceRepository = monthlyBalanceRepository;
        this.monthlyBalanceAdapter = monthlyBalanceAdapter;
    }

    @Override
    public Mono<MonthlyBalance> createMonthlyBalance(MonthlyBalance monthlyBalance) {
        MonthlyBalanceEntity entity = monthlyBalanceAdapter.toEntity(monthlyBalance);
    return monthlyBalanceRepository.save(entity)
        .map(entityResult -> monthlyBalanceAdapter.toModel(entityResult));
    }

    @Override
    public Mono<MonthlyBalance> updateMonthlyBalance(Long id, MonthlyBalance monthlyBalance) {
        return monthlyBalanceRepository.findById(id)
                .flatMap(existing -> {
                    MonthlyBalanceEntity updated = monthlyBalanceAdapter.toEntity(monthlyBalance);
                    updated.setId(id);
                    return monthlyBalanceRepository.save(updated);
                })
                .map(entityResult -> monthlyBalanceAdapter.toModel(entityResult));
    }

    @Override
    public Mono<Void> deleteMonthlyBalance(Long id) {
        return monthlyBalanceRepository.deleteById(id);
    }

    @Override
    public Mono<MonthlyBalance> getMonthlyBalanceById(Long id) {
    return monthlyBalanceRepository.findById(id)
        .map(entityResult -> monthlyBalanceAdapter.toModel(entityResult));
    }

    @Override
    public Flux<MonthlyBalance> getAllMonthlyBalances() {
    return monthlyBalanceRepository.findAll()
        .map(entityResult -> monthlyBalanceAdapter.toModel(entityResult));
    }
}
