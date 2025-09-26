package co.com.soft.adapters;

import co.com.soft.entity.LoanEntity;
import co.com.soft.model.Loan;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LoanAdapter {
    public static Loan toModel(LoanEntity entity) {
        if (entity == null) return null;
        LocalDateTime loanDate = entity.getLoanDate() != null ? entity.getLoanDate().toLocalDateTime() : null;
        LocalDateTime limitToPayDate = entity.getLimitToPayDate() != null ? entity.getLimitToPayDate().toLocalDateTime() : null;
        return new Loan(entity.getId(), entity.getIdUser(), entity.getLender(), entity.getAmount(), loanDate, limitToPayDate, entity.getStatus());
    }

    public static LoanEntity toEntity(Loan model) {
        if (model == null) return null;
        Timestamp loanDate = model.getLoanDate() != null ? Timestamp.valueOf(model.getLoanDate()) : null;
        Timestamp limitToPayDate = model.getLimitToPayDate() != null ? Timestamp.valueOf(model.getLimitToPayDate()) : null;
        return new LoanEntity(model.getId(), model.getIdUser(), model.getLender(), model.getAmount(), loanDate, limitToPayDate, model.getStatus());
    }
}
