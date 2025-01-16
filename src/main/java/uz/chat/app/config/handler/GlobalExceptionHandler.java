package uz.chat.app.config.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.chat.app.dto.AppErrorDto;
import uz.chat.app.exception.InvalidDataException;
import uz.chat.app.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());

        AppErrorDto dto = AppErrorDto.builder()
                .errorMessage(String.join(", ", errorMessages))
                .path(request.getRequestURI())
                .errorCode(400)
                .timestamp(LocalDateTime.now())
                .build();

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

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<AppErrorDto> handleInvalidDataException(InvalidDataException ex, HttpServletRequest request) {
        AppErrorDto err = AppErrorDto.builder()
                .errorCode(400)
                .errorMessage(ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AppErrorDto> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        AppErrorDto err = AppErrorDto.builder()
                .errorCode(500)
                .errorMessage(ex.getMessage())
                .path(request.getRequestURI())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(500).body(err);
    }

}
