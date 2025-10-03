package co.com.soft.mapper;

import co.com.soft.dto.FixedExpenseCreateDTO;
import co.com.soft.dto.FixedExpenseDTO;
import co.com.soft.dto.FixedExpenseUpdateDTO;
import co.com.soft.model.FixedExpense;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class FixedExpenseMapper {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_DATE_TIME;
    private FixedExpenseMapper() {}

    public static FixedExpense toModelFromCreate(FixedExpenseCreateDTO dto) {
        if (dto == null) return null;
        LocalDateTime s = dto.getStartDate() == null ? null : LocalDateTime.parse(dto.getStartDate(), FMT);
        LocalDateTime e = dto.getEndDate() == null ? null : LocalDateTime.parse(dto.getEndDate(), FMT);
        return new FixedExpense(null, dto.getIdUser(), dto.getAmount(), dto.getDescription(), dto.getFrequency(), s, e);
    }

    public static FixedExpense toModelFromUpdate(FixedExpenseUpdateDTO dto) {
        if (dto == null) return null;
        LocalDateTime s = dto.getStartDate() == null ? null : LocalDateTime.parse(dto.getStartDate(), FMT);
        LocalDateTime e = dto.getEndDate() == null ? null : LocalDateTime.parse(dto.getEndDate(), FMT);
        return new FixedExpense(dto.getId(), dto.getIdUser(), dto.getAmount(), dto.getDescription(), dto.getFrequency(), s, e);
    }

    public static FixedExpenseDTO toDTO(FixedExpense model) {
        if (model == null) return null;
        String s = model.getStartDate() == null ? null : model.getStartDate().format(FMT);
        String e = model.getEndDate() == null ? null : model.getEndDate().format(FMT);
        String freq = model.getFrequency() == null ? null : model.getFrequency().name();
        return new FixedExpenseDTO(model.getId(), model.getIdUser(), model.getAmount(), model.getDescription(), freq, s, e);
    }
}
