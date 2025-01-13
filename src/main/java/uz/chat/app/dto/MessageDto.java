package uz.chat.app.dto;

import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link uz.chat.app.entity.Message}
 */
public record MessageDto(Long senderId, Long recipientId, String content, Long chatId) {
}