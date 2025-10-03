package co.com.soft.dto;

import co.com.soft.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountUpdateDTO {
    private Long id;
    private Long idUser;
    private String name;
    private AccountType type;
    private Long balance;
}
