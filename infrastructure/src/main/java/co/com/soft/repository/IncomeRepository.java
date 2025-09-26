package co.com.soft.repository;

import co.com.soft.entity.IncomeEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IncomeRepository extends ReactiveCrudRepository<IncomeEntity, Long> {
}