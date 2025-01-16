package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.service.UsersService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("isAuthenticated()")
@PreFilter("hasRole('ADMIN')")
public class AdminController {

    private final UsersService usersService;

    @PostMapping("/makeAdmin/{userId}")
    public ResponseEntity<Users> makeAdmin(@PathVariable Long userId) {
        return ResponseEntity.ok(usersService.makeAdminById(userId));
    }

    @PostMapping("suspendUser/{userId}")
    public ResponseEntity<Users> suspendUser(@PathVariable Long userId) {
        return ResponseEntity.ok(usersService.blockUserById(userId));
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

}
