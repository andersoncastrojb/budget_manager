package co.com.soft.dto;

import co.com.soft.enums.PayStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoanUpdateDTO {
    private Long id;
    private Long idUser;
    private String lender;
    private Long amount;
    private String loanDate;
    private String limitToPayDate;
    private PayStatus status;
}
