package co.com.soft.repository;

import co.com.soft.entity.Loan;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface LoanRepository extends ReactiveCrudRepository<Loan, Long> {
}