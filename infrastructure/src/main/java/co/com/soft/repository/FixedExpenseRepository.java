package co.com.soft.repository;

import co.com.soft.entity.FixedExpenseEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface FixedExpenseRepository extends ReactiveCrudRepository<FixedExpenseEntity, Long> {
}