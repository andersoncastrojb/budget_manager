package co.com.soft.mapper;

import co.com.soft.dto.LoanCreateDTO;
import co.com.soft.dto.LoanDTO;
import co.com.soft.dto.LoanUpdateDTO;
import co.com.soft.model.Loan;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class LoanMapper {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_DATE_TIME;
    private LoanMapper() {}

    public static Loan toModelFromCreate(LoanCreateDTO dto) {
        if (dto == null) return null;
        LocalDateTime loanDate = dto.getLoanDate() == null ? null : LocalDateTime.parse(dto.getLoanDate(), FMT);
        LocalDateTime limit = dto.getLimitToPayDate() == null ? null : LocalDateTime.parse(dto.getLimitToPayDate(), FMT);
        return new Loan(null, dto.getIdUser(), dto.getLender(), dto.getAmount(), loanDate, limit, dto.getStatus());
    }

    public static Loan toModelFromUpdate(LoanUpdateDTO dto) {
        if (dto == null) return null;
        LocalDateTime loanDate = dto.getLoanDate() == null ? null : LocalDateTime.parse(dto.getLoanDate(), FMT);
        LocalDateTime limit = dto.getLimitToPayDate() == null ? null : LocalDateTime.parse(dto.getLimitToPayDate(), FMT);
        return new Loan(dto.getId(), dto.getIdUser(), dto.getLender(), dto.getAmount(), loanDate, limit, dto.getStatus());
    }

    public static LoanDTO toDTO(Loan model) {
        if (model == null) return null;
        String loanDate = model.getLoanDate() == null ? null : model.getLoanDate().format(FMT);
        String limit = model.getLimitToPayDate() == null ? null : model.getLimitToPayDate().format(FMT);
        String status = model.getStatus() == null ? null : model.getStatus().name();
        return new LoanDTO(model.getId(), model.getIdUser(), model.getLender(), model.getAmount(), loanDate, limit, status);
    }
}
