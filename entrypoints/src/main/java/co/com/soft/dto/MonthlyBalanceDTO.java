package co.com.soft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonthlyBalanceDTO {
    private Long id;
    private Long idUser;
    private Integer month;
    private Integer year;
    private Long totalIncomes;
    private Long totalExpenses;
    private Long loans;
    private Long finalBalance;
}
