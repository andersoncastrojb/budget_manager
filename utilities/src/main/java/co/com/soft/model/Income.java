package co.com.soft.model;

import co.com.soft.enums.IncomeType;
import java.time.LocalDateTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Income {
    private Long id;
    private Long idAccount;
    private IncomeType type;
    private Long value;
    private String description;
    private LocalDateTime date;
}
