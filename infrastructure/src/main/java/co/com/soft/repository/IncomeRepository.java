package co.com.soft.repository;

import co.com.soft.entity.IncomeEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IncomeRepository extends ReactiveCrudRepository<IncomeEntity, Long> {
    @Query("SELECT * FROM incomes WHERE id_account = :idAccount")
    Flux<IncomeEntity> findAllByAccountId(Long idAccount);

    @Query("SELECT i.* FROM incomes i INNER JOIN accounts a ON i.id_account = a.id WHERE a.id_user = :userId")
    Flux<IncomeEntity> findAllByUserId(Long userId);
}