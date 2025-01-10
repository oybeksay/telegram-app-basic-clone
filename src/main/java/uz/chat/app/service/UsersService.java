package uz.chat.app.service;

import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.Users;

public interface UsersService {

    Users createUser(UsersDto usersDto);

    Users findUserById(Long id);

    Users updateUser(Long id, UsersDto usersDto);

    void deleteUser(Long id);

}
