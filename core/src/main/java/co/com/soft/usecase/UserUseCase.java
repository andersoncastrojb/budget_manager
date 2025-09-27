package co.com.soft.usecase;

import co.com.soft.model.User;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface UserUseCase {
    Mono<User> createUser(User user);
    Mono<User> updateUser(Long id, User user);
    Mono<Void> deleteUser(Long id);
    Mono<User> getUserById(Long id);
    Flux<User> getAllUsers();
}
