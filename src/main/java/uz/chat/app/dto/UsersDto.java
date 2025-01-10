package uz.chat.app.dto;

import java.io.Serializable;

/**
 * DTO for {@link uz.chat.app.entity.Users}
 */
public record UsersDto(String username, String fullName, String phoneNumber, String password) implements Serializable {
}