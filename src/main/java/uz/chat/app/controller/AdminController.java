package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.service.UsersService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UsersService usersService;

    @PostMapping("/makeAdmin/{userId}")
    public Users makeAdmin(@PathVariable Long userId) {
        return ResponseEntity.ok(usersService.makeAdminById(userId));
    }

}
