
package co.com.soft.adapters;
import org.springframework.stereotype.Component;

import co.com.soft.entity.IncomeEntity;
import co.com.soft.model.Income;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class IncomeAdapter {
    public static Income toModel(IncomeEntity entity) {
        if (entity == null) return null;
        LocalDateTime date = entity.getDate() != null ? entity.getDate().toLocalDateTime() : null;
    return new Income(entity.getId(), entity.getIdAccount(), entity.getType(), entity.getValue(), entity.getDescription(), date);
    }

    public static IncomeEntity toEntity(Income model) {
        if (model == null) return null;
        Timestamp date = model.getDate() != null ? Timestamp.valueOf(model.getDate()) : null;
        return new IncomeEntity(model.getId(), model.getIdAccount(), model.getType(), model.getValue(), model.getDescription(), date);
    }
}
