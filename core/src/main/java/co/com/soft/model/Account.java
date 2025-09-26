package co.com.soft.model;

import co.com.soft.enums.AccountType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account {
    private Long id;
    private Integer idUser;
    private String name;
    private AccountType type;
    private Long balance;
}
