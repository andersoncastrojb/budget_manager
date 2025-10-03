package co.com.soft.mapper;

import co.com.soft.dto.AccountCreateDTO;
import co.com.soft.dto.AccountDTO;
import co.com.soft.dto.AccountUpdateDTO;
import co.com.soft.model.Account;

public final class AccountMapper {
    private AccountMapper() {}

    public static Account toModelFromCreate(AccountCreateDTO dto) {
        if (dto == null) return null;
        return new Account(null, dto.getIdUser(), dto.getName(), dto.getType(), dto.getBalance());
    }

    public static Account toModelFromUpdate(AccountUpdateDTO dto) {
        if (dto == null) return null;
        return new Account(dto.getId(), dto.getIdUser(), dto.getName(), dto.getType(), dto.getBalance());
    }

    public static AccountDTO toDTO(Account model) {
        if (model == null) return null;
        String type = model.getType() == null ? null : model.getType().name();
        return new AccountDTO(model.getId(), model.getIdUser(), model.getName(), type, model.getBalance());
    }
}
