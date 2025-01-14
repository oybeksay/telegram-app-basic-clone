package uz.chat.app.dto;

import uz.chat.app.entity.auth.Users;

import java.io.Serializable;

/**
 * DTO for {@link Users}
 */
public record UsersDto(String username, String fullName, String email, String password) implements Serializable {
}