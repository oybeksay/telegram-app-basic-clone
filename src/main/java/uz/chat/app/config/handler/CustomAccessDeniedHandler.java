package uz.chat.app.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import uz.chat.app.dto.AppErrorDto;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String errorPath = request.getRequestURI();
        String errorMessage = accessDeniedException.getMessage();
        int errorCode = 403;
        AppErrorDto errorDTO = new AppErrorDto(errorMessage, errorCode, errorPath, LocalDateTime.now());
        response.setStatus(errorCode);
        ServletOutputStream out = response.getOutputStream();
        objectMapper.writeValue(out, errorDTO);
    }

}
