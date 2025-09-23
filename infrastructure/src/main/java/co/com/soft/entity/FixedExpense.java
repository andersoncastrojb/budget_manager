package co.com.soft.entity;

import co.com.soft.enums.FrequencyToPay;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("fixed_expenses")
public class FixedExpense {
    @Id
    private Long id;

    @Column("id_user")
    private Integer idUser;
    private Long amount;
    private String description;
    private FrequencyToPay frequency;
    @Column("start_date")
    private String startDate;
    @Column("end_date")
    private String endDate;
}