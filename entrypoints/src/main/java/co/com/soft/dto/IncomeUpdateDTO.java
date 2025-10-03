package co.com.soft.dto;

import co.com.soft.enums.IncomeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IncomeUpdateDTO {
    private Long id;
    private Long idAccount;
    private IncomeType type;
    private Long value;
    private String description;
    private String date;
}
