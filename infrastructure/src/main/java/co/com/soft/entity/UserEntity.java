package co.com.soft.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("users")
public class UserEntity {
    @Id
    private Integer id;
    private String name;
    private String email;
    private String password;
}