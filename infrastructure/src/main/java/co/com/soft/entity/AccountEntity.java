package co.com.soft.entity;

import co.com.soft.enums.AccountType;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("accounts")
public class AccountEntity {
    @Id
    private Long id;

    @Column("id_user")
    private Long idUser;
    private String name;
    private AccountType type;
    private Long balance;
}