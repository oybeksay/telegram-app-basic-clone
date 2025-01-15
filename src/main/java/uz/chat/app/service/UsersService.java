package uz.chat.app.service;

import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.auth.Users;

public interface UsersService {

    Users createUser(UsersDto usersDto);

    Users findUserById(Long id);

    Users findUserByUsername(String username);

    Users updateUser(Long id, UsersDto usersDto);

    void updateUserEnabled(Long id, boolean enabled);

    void deleteUser(Long id);

}
