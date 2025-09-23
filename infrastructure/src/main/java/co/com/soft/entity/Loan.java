package co.com.soft.entity;

import co.com.soft.enums.PayStatus;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("loans")
public class Loan {
    @Id
    private Long id;

    @Column("id_user")
    private Integer idUser;
    private String lender;
    private Long amount;
    @Column("loan_date")
    private String loanDate;
    @Column("limit_to_pay_date")
    private String limitToPayDate;
    private PayStatus status;
}