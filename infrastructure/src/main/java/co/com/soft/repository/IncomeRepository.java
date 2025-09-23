package co.com.soft.repository;

import co.com.soft.entity.Income;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IncomeRepository extends ReactiveCrudRepository<Income, Long> {
}