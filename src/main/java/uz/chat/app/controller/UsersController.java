package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.service.UsersService;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<Users> createUser(@RequestBody UsersDto usersDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(usersDto));
    }

    @PostMapping("/avatar/upload/{userId}")
    public ResponseEntity<Users> uploadAvatar(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(usersService.uploadAvatarByUserId(userId,file));
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Users> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.findUserById(id));
    }

    @GetMapping("/search")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Users>> findUsersByNameOrEmail(@RequestParam String query) {
        return ResponseEntity.ok(usersService.findUsersByUsernameOrEmail(query));
    }


    @PutMapping("/update/{id}")
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

