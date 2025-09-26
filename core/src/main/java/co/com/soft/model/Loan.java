package co.com.soft.model;

import co.com.soft.enums.PayStatus;
import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Loan {
    private Long id;
    private Integer idUser;
    private String lender;
    private Long amount;
    private LocalDateTime loanDate;
    private LocalDateTime limitToPayDate;
    private PayStatus status;
}
