package co.com.soft.adapters;

import co.com.soft.entity.AccountEntity;
import co.com.soft.model.Account;

public class AccountAdapter {
    public static Account toModel(AccountEntity entity) {
        if (entity == null) return null;
        return new Account(entity.getId(), entity.getIdUser(), entity.getName(), entity.getType(), entity.getBalance());
    }

    public static AccountEntity toEntity(Account model) {
        if (model == null) return null;
        return new AccountEntity(model.getId(), model.getIdUser(), model.getName(), model.getType(), model.getBalance());
    }
}
