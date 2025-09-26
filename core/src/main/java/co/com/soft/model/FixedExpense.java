package co.com.soft.model;

import co.com.soft.enums.FrequencyToPay;
import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FixedExpense {
    private Long id;
    private Integer idUser;
    private Long amount;
    private String description;
    private FrequencyToPay frequency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
