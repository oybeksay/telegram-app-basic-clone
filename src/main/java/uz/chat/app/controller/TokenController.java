package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.chat.app.config.security.jwt.JwtUtil;
import uz.chat.app.service.UserOtpService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserOtpService userOtpService;

    @GetMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestParam String username,
                                                    @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        Map<String, String> tokens = Map.of(
                "refreshToken", jwtUtil.generateRefreshToken(username), "accessToken", jwtUtil.generateAccessToken(username)
        );
        return ResponseEntity.ok(tokens);
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<Map<String,String>> createAccessToken(@RequestParam String refreshToken) {
        return ResponseEntity.ok(Map.of("refreshToken", jwtUtil.generateRefreshToken(refreshToken)));
    }

    @GetMapping("/activation/{token}")
    public ResponseEntity<String> activationAccount(@PathVariable String token) {
        return ResponseEntity.ok(userOtpService.checkActivationAccount(token));
    }

}
