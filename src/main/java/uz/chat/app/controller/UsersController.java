package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.auth.UserOtp;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.service.UserOtpService;
import uz.chat.app.service.UsersService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UsersController {

    private final UsersService usersService;
    private final UserOtpService userOtpService;

    @PostMapping("/create")
    public ResponseEntity<Users> createUser(@RequestBody UsersDto usersDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(usersDto));
    }

    @PostMapping("/test")
    public ResponseEntity<UserOtp> testUser(@RequestBody UserOtp userOtp) {
        return ResponseEntity.ok(userOtpService.createUserOtp(userOtp));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Users> findUserById(@PathVariable Long id) {
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
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

