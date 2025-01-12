package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.chat.app.config.security.jwt.JwtUtil;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.Users;
import uz.chat.app.service.UsersService;

import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UsersController {

    private final UsersService usersService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/create")
    public ResponseEntity<Users> login(@RequestBody UsersDto usersDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(usersDto));
    }

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


    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.findUserById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Users> updateUser(@PathVariable Long id,
                                            @RequestBody UsersDto usersDto) {
        return ResponseEntity.ok(usersService.updateUser(id, usersDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

