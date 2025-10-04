package co.com.soft.repository;

import co.com.soft.entity.MonthlyBalanceEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MonthlyBalanceRepository extends ReactiveCrudRepository<MonthlyBalanceEntity, Long> {
    @Query("SELECT * FROM monthly_balances WHERE id_user = :idUser")
    Flux<MonthlyBalanceEntity> findAllByUserId(Long idUser);
    @Query("SELECT * FROM monthly_balances WHERE id_user = :idUser AND month = :month AND year = :year LIMIT 1")
    Mono<MonthlyBalanceEntity> findByUserIdAndMonthAndYear(Long idUser, Integer month, Integer year);
}