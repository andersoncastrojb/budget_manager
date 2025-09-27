
package co.com.soft.adapters;
import org.springframework.stereotype.Component;

import co.com.soft.entity.MonthlyBalanceEntity;
import co.com.soft.model.MonthlyBalance;

@Component
public class MonthlyBalanceAdapter {
    public static MonthlyBalance toModel(MonthlyBalanceEntity entity) {
        if (entity == null) return null;
        return new MonthlyBalance(entity.getId(), entity.getIdUser(), entity.getMonth(), entity.getYear(), entity.getTotalIncomes(), entity.getTotalExpenses(), entity.getLoans(), entity.getFinalBalance());
    }

    public static MonthlyBalanceEntity toEntity(MonthlyBalance model) {
        if (model == null) return null;
        return new MonthlyBalanceEntity(model.getId(), model.getIdUser(), model.getMonth(), model.getYear(), model.getTotalIncomes(), model.getTotalExpenses(), model.getLoans(), model.getFinalBalance());
    }
}
