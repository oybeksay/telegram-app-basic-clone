package uz.chat.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.Users;
import uz.chat.app.mapper.UsersMapper;
import uz.chat.app.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersMapper usersMapper;
    private final UsersRepository usersRepository;

    @Override
    public Users createUser(UsersDto usersDto) {
        Users user = usersMapper.toEntity(usersDto);
        return usersRepository.save(user);
    }

    @Override
    public Users findUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public Users updateUser(Long id, UsersDto usersDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
