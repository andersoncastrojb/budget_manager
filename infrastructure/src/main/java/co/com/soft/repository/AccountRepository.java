package co.com.soft.repository;

import co.com.soft.entity.AccountEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AccountRepository extends ReactiveCrudRepository<AccountEntity, Long> {
}