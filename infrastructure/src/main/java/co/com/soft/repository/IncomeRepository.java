package co.com.soft.repository;

import co.com.soft.entity.IncomeEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IncomeRepository extends ReactiveCrudRepository<IncomeEntity, Long> {
    @Query("SELECT * FROM incomes WHERE id_account = :idAccount")
    Flux<IncomeEntity> findAllByAccountId(Long idAccount);
}