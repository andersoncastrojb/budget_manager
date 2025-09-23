package co.com.soft.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("monthly_balances")
public class MonthlyBalance {
    @Id
    private Long id;

    @Column("id_user")
    private Integer idUser;
    private Integer month;
    private Integer year;
    @Column("total_incomes")
    private Long totalIncomes;
    @Column("total_expenses")
    private Long totalExpenses;
    private Long loans;
    @Column("final_balance")
    private Long finalBalance;
}