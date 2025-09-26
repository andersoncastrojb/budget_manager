package co.com.soft.adapters;

import co.com.soft.entity.FixedExpenseEntity;
import co.com.soft.model.FixedExpense;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FixedExpenseAdapter {
    public static FixedExpense toModel(FixedExpenseEntity entity) {
        if (entity == null) return null;
        LocalDateTime startDate = entity.getStartDate() != null ? entity.getStartDate().toLocalDateTime() : null;
        LocalDateTime endDate = entity.getEndDate() != null ? entity.getEndDate().toLocalDateTime() : null;
        return new FixedExpense(entity.getId(), entity.getIdUser(), entity.getAmount(), entity.getDescription(), entity.getFrequency(), startDate, endDate);
    }

    public static FixedExpenseEntity toEntity(FixedExpense model) {
        if (model == null) return null;
        Timestamp startDate = model.getStartDate() != null ? Timestamp.valueOf(model.getStartDate()) : null;
        Timestamp endDate = model.getEndDate() != null ? Timestamp.valueOf(model.getEndDate()) : null;
        return new FixedExpenseEntity(model.getId(), model.getIdUser(), model.getAmount(), model.getDescription(), model.getFrequency(), startDate, endDate);
    }
}
