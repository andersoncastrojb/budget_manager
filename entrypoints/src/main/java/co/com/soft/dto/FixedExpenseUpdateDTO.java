package co.com.soft.dto;

import co.com.soft.enums.FrequencyToPay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FixedExpenseUpdateDTO {
    private Long id;
    private Long idUser;
    private Long amount;
    private String description;
    private FrequencyToPay frequency;
    private String startDate;
    private String endDate;
}
