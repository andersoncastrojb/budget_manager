package co.com.soft.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IncomeDTO {
    private Long id;
    private Long idAccount;
    private String type;
    private Long value;
    private String description;
    private String date;
}
