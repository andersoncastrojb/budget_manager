package co.com.soft.mapper;

import co.com.soft.dto.UserCreateDTO;
import co.com.soft.dto.UserDTO;
import co.com.soft.dto.UserUpdateDTO;
import co.com.soft.model.User;

public final class UserMapper {
    private UserMapper() {}

    public static User toModelFromCreate(UserCreateDTO dto) {
        if (dto == null) return null;
        return new User(null, dto.getName(), dto.getEmail(), dto.getPassword());
    }

    public static User toModelFromUpdate(UserUpdateDTO dto) {
        if (dto == null) return null;
        return new User(dto.getId(), dto.getName(), dto.getEmail(), dto.getPassword());
    }

    public static UserDTO toDTO(User model) {
        if (model == null) return null;
        return new UserDTO(model.getId(), model.getName(), model.getEmail());
    }
}
