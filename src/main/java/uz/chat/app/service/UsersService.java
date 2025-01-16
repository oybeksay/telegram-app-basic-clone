package uz.chat.app.service;

import org.springframework.web.multipart.MultipartFile;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.auth.Users;

import java.util.List;

public interface UsersService {

    Users createUser(UsersDto usersDto);

    Users findUserById(Long id);

    Users findUserByUsername(String username);

    Users updateUser(Long id, UsersDto usersDto);

    void updateUserEnabled(Long id, boolean enabled);

    void deleteUser(Long id);

    List<Users> findUsersByUsernameOrEmail(String query);

    Users uploadAvatarByUserId(Long userId, MultipartFile file);

    Users makeAdminById(Long userId);
}
