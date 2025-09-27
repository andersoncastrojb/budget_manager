package co.com.soft.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
}
