package co.com.soft.service;

import co.com.soft.usecase.UserUseCase;
import co.com.soft.model.User;
import co.com.soft.adapters.UserAdapter;
import co.com.soft.entity.UserEntity;
import co.com.soft.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@Service
public class UserService implements UserUseCase {
    private final UserRepository userRepository;
    private final UserAdapter userAdapter;

    @Autowired
    public UserService(UserRepository userRepository, UserAdapter userAdapter) {
        this.userRepository = userRepository;
        this.userAdapter = userAdapter;
    }

    @Override
    public Mono<User> createUser(User user) {
        UserEntity entity = userAdapter.toEntity(user);
    return userRepository.save(entity)
        .map(entityResult -> userAdapter.toModel(entityResult));
    }

    @Override
    public Mono<User> updateUser(Long id, User user) {
        return userRepository.findById(id)
                .flatMap(existing -> {
                    UserEntity updated = userAdapter.toEntity(user);
                    updated.setId(id);
                    return userRepository.save(updated);
                })
                .map(entityResult -> userAdapter.toModel(entityResult));
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Mono<User> getUserById(Long id) {
    return userRepository.findById(id)
        .map(entityResult -> userAdapter.toModel(entityResult));
    }

    @Override
    public Flux<User> getAllUsers() {
    return userRepository.findAll()
        .map(entityResult -> userAdapter.toModel(entityResult));
    }
}
