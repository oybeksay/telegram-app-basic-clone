package uz.chat.app.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.Users;
import uz.chat.app.service.UsersService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> login(@RequestBody UsersDto usersDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.createUser(usersDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.findUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id,
                                            @RequestBody UsersDto usersDto) {
        return ResponseEntity.ok(usersService.updateUser(id, usersDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}

