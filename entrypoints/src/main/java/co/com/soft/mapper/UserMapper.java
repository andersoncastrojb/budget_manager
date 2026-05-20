package co.com.soft.mapper;

import co.com.soft.dto.UserCreateDTO;
import co.com.soft.dto.UserDTO;
import co.com.soft.dto.UserUpdateDTO;
import co.com.soft.model.User;
import co.com.soft.model.UserDashboard;
import co.com.soft.dto.UserDashboardDTO;
import java.util.stream.Collectors;

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

    public static UserDashboardDTO toDashboardDTO(UserDashboard model) {
        if (model == null) return null;

        UserDashboardDTO dto = new UserDashboardDTO();
        dto.setUser(toDTO(model.getUser()));

        dto.setAccounts(model.getAccounts().stream().map(AccountMapper::toDTO).collect(Collectors.toList()));
        dto.setLoans(model.getLoans().stream().map(LoanMapper::toDTO).collect(Collectors.toList()));
        dto.setFixedExpenses(model.getFixedExpenses().stream().map(FixedExpenseMapper::toDTO).collect(Collectors.toList()));
        dto.setIncomes(model.getIncomes().stream().map(IncomeMapper::toDTO).collect(Collectors.toList()));

        return dto;
    }
}
