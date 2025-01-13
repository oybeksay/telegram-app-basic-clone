package uz.chat.app.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.chat.app.domein.Role;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String password;

    private Role role = Role.USER; //set default value for users
}
