package co.com.soft.repository;

import co.com.soft.entity.FixedExpenseEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface FixedExpenseRepository extends ReactiveCrudRepository<FixedExpenseEntity, Long> {
    @Query("SELECT * FROM fixed_expenses WHERE id_user = :idUser")
    Flux<FixedExpenseEntity> findAllByUserId(Long idUser);
}