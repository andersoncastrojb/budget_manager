package co.com.soft.controller;

import co.com.soft.model.User;
import co.com.soft.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserUseCase userUseCase;

    @Autowired
    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody User user) {
        return userUseCase.createUser(user);
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return userUseCase.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        return userUseCase.deleteUser(id);
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable("id") Long id) {
        return userUseCase.getUserById(id);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userUseCase.getAllUsers();
    }
}
