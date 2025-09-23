package co.com.soft.repository;

import co.com.soft.entity.FixedExpense;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FixedExpenseRepository extends ReactiveCrudRepository<FixedExpense, Long> {
}