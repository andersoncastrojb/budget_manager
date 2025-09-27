package co.com.soft.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonthlyBalance {
    private Long id;
    private Long idUser;
    private Integer month;
    private Integer year;
    private Long totalIncomes;
    private Long totalExpenses;
    private Long loans;
    private Long finalBalance;
}
