package co.com.soft.repository;

import co.com.soft.entity.LoanEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface LoanRepository extends ReactiveCrudRepository<LoanEntity, Long> {
    @Query("SELECT * FROM loans WHERE id_user = :idUser")
    Flux<LoanEntity> findAllByUserId(Long idUser);
}