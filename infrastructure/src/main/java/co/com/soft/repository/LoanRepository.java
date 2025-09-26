package co.com.soft.repository;

import co.com.soft.entity.LoanEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LoanRepository extends ReactiveCrudRepository<LoanEntity, Long> {
}