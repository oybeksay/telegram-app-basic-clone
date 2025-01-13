package uz.chat.app.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.chat.app.dto.AppErrorDto;
import uz.chat.app.exception.ResourceNotFoundException;

import java.time.LocalDateTime;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        AppErrorDto dto = AppErrorDto.builder()
                .errorMessage(ex.getMessage())
                .path(request.getRequestURI())
                .errorCode(400)
                .timestamp(LocalDateTime.now()).build();
        return ResponseEntity.badRequest().body(dto);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppErrorDto> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request) {
        AppErrorDto err = AppErrorDto.builder()
                .errorMessage(ex.getMessage())
                .path(request.getRequestURI())
                .errorCode(404)
                .timestamp(LocalDateTime.now()).build();
        return ResponseEntity.status(404).body(err);
    }
}
