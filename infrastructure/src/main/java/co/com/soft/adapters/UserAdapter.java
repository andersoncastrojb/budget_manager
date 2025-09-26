package co.com.soft.adapters;

import co.com.soft.entity.UserEntity;
import co.com.soft.model.User;

public class UserAdapter {
    public static User toModel(UserEntity entity) {
        if (entity == null) return null;
        return new User(entity.getId(), entity.getName(), entity.getEmail(), entity.getPassword());
    }

    public static UserEntity toEntity(User model) {
        if (model == null) return null;
        return new UserEntity(model.getId(), model.getName(), model.getEmail(), model.getPassword());
    }
}
