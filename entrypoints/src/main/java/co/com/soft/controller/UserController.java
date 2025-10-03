package co.com.soft.controller;

import co.com.soft.dto.UserCreateDTO;
import co.com.soft.dto.UserDTO;
import co.com.soft.dto.UserUpdateDTO;
import co.com.soft.mapper.UserMapper;
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
    public Mono<UserDTO> createUser(@RequestBody UserCreateDTO createDto) {
        User model = UserMapper.toModelFromCreate(createDto);
        return userUseCase.createUser(model)
                .map(UserMapper::toDTO);
    }

    @PutMapping
    public Mono<UserDTO> updateUser(@RequestBody UserUpdateDTO updateDto) {
        User model = UserMapper.toModelFromUpdate(updateDto);
        Long id = model.getId();
        return userUseCase.updateUser(id, model)
                .map(UserMapper::toDTO);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable("id") Long id) {
        return userUseCase.deleteUser(id);
    }

    @GetMapping("/{id}")
    public Mono<UserDTO> getUserById(@PathVariable("id") Long id) {
        return userUseCase.getUserById(id).map(UserMapper::toDTO);
    }

    @GetMapping
    public Flux<UserDTO> getAllUsers() {
        return userUseCase.getAllUsers().map(UserMapper::toDTO);
    }
}
