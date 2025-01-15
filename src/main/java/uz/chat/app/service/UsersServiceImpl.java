package uz.chat.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.chat.app.domein.Role;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.event.UserEvent;
import uz.chat.app.exception.ResourceNotFoundException;
import uz.chat.app.mapper.UsersMapper;
import uz.chat.app.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersMapper usersMapper;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Users createUser(UsersDto usersDto) {
        Users user = usersMapper.toEntity(usersDto);
        //encode user password with bcrypt alg
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // set user role
        user.setRole(Role.USER);
        Users entity = usersRepository.save(user);
        // published user to listener
        eventPublisher.publishEvent(new UserEvent(entity));
        return user;
    }

    @Override
    public Users findUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Users findUserByUsername(String username) {
        return usersRepository.findUserByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Users updateUser(Long id, UsersDto usersDto) {
        return null;
    }

    @Override
    public void updateUserEnabled(Long id, boolean enabled) {
        usersRepository.updateEnabledById(enabled,id);
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }
}
