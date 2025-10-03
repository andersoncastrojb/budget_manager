package co.com.soft.mapper;

import co.com.soft.dto.IncomeCreateDTO;
import co.com.soft.dto.IncomeDTO;
import co.com.soft.dto.IncomeUpdateDTO;
import co.com.soft.model.Income;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class IncomeMapper {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ISO_DATE_TIME;
    private IncomeMapper() {}

    public static Income toModelFromCreate(IncomeCreateDTO dto) {
        if (dto == null) return null;
        LocalDateTime date = dto.getDate() == null ? null : LocalDateTime.parse(dto.getDate(), FMT);
    return new Income(null, dto.getIdAccount(), dto.getType(), dto.getValue(), dto.getDescription(), date);
    }

    public static Income toModelFromUpdate(IncomeUpdateDTO dto) {
        if (dto == null) return null;
        LocalDateTime date = dto.getDate() == null ? null : LocalDateTime.parse(dto.getDate(), FMT);
    return new Income(dto.getId(), dto.getIdAccount(), dto.getType(), dto.getValue(), dto.getDescription(), date);
    }

    public static IncomeDTO toDTO(Income model) {
        if (model == null) return null;
        String date = model.getDate() == null ? null : model.getDate().format(FMT);
        String type = model.getType() == null ? null : model.getType().name();
        return new IncomeDTO(model.getId(), model.getIdAccount(), type, model.getValue(), model.getDescription(), date);
    }
}
