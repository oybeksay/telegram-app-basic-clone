package uz.chat.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AppErrorDto {
    private String errorMessage;
    private int errorCode;
    private String path;
    private LocalDateTime timestamp = LocalDateTime.now();
}
