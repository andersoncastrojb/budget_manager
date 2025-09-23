package co.com.soft.entity;

import co.com.soft.enums.IncomeType;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("incomes")
public class Income {
    @Id
    private Long id;

    @Column("id_user")
    private Integer idUser;
    private IncomeType type;
    private Long value;
    private String description;
    private String date;
}