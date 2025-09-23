package co.com.soft.repository;

import co.com.soft.entity.MonthlyBalance;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MonthlyBalanceRepository extends ReactiveCrudRepository<MonthlyBalance, Long> {
}