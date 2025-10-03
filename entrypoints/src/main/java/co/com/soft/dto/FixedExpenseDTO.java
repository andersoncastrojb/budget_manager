package co.com.soft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FixedExpenseDTO {
    private Long id;
    private Long idUser;
    private Long amount;
    private String description;
    private String frequency;
    private String startDate;
    private String endDate;
}
