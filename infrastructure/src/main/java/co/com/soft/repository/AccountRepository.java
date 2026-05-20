package co.com.soft.repository;

import co.com.soft.entity.AccountEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Long> {
    @Query("SELECT * FROM accounts WHERE id_user = :idUser")
    Flux<AccountEntity> findAllByUserId(Long idUser);
}