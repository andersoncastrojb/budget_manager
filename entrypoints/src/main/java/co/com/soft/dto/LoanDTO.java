package co.com.soft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoanDTO {
    private Long id;
    private Long idUser;
    private String lender;
    private Long amount;
    private String loanDate;
    private String limitToPayDate;
    private String status;
}
