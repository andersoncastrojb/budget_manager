package co.com.soft.repository;

import co.com.soft.entity.MonthlyBalanceEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MonthlyBalanceRepository extends ReactiveCrudRepository<MonthlyBalanceEntity, Long> {
}