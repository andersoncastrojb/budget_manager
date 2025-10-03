package co.com.soft.mapper;

import co.com.soft.dto.MonthlyBalanceCreateDTO;
import co.com.soft.dto.MonthlyBalanceDTO;
import co.com.soft.dto.MonthlyBalanceUpdateDTO;
import co.com.soft.model.MonthlyBalance;

public final class MonthlyBalanceMapper {
    private MonthlyBalanceMapper() {}

    public static MonthlyBalance toModelFromCreate(MonthlyBalanceCreateDTO dto) {
        if (dto == null) return null;
        return new MonthlyBalance(null, dto.getIdUser(), dto.getMonth(), dto.getYear(), dto.getTotalIncomes(), dto.getTotalExpenses(), dto.getLoans(), dto.getFinalBalance());
    }

    public static MonthlyBalance toModelFromUpdate(MonthlyBalanceUpdateDTO dto) {
        if (dto == null) return null;
        return new MonthlyBalance(dto.getId(), dto.getIdUser(), dto.getMonth(), dto.getYear(), dto.getTotalIncomes(), dto.getTotalExpenses(), dto.getLoans(), dto.getFinalBalance());
    }

    public static MonthlyBalanceDTO toDTO(MonthlyBalance model) {
        if (model == null) return null;
        return new MonthlyBalanceDTO(model.getId(), model.getIdUser(), model.getMonth(), model.getYear(), model.getTotalIncomes(), model.getTotalExpenses(), model.getLoans(), model.getFinalBalance());
    }
}
