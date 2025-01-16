package uz.chat.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.chat.app.domein.Role;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.event.UserEvent;
import uz.chat.app.exception.ResourceNotFoundException;
import uz.chat.app.mapper.UsersMapper;
import uz.chat.app.repository.UsersRepository;
import uz.chat.app.util.FileService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final UsersMapper usersMapper;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final FileService fileService;

    @Override
    public Users createUser(UsersDto usersDto) {
        Users user = usersMapper.toEntity(usersDto);
        //encode user password with bcrypt alg
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // set user role
        user.setRole(Role.USER);
        user.setLocked(true);
        Users entity = usersRepository.save(user);
        // published user to listener
        eventPublisher.publishEvent(new UserEvent(entity));
        return entity;
    }

    @Override
    public Users findUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));
    }

    @Override
    public Users findUserByUsername(String username) {
        return usersRepository.findUserByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));
    }

    @Override
    public Users updateUser(Long id, UsersDto usersDto) {
        Users user = findUserById(id);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        return usersRepository.save(user);
    }

    @Override
    public void updateUserEnabled(Long id, boolean enabled) {
        usersRepository.updateEnabledById(enabled,id);
    }

    @Override
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public List<Users> findUsersByUsernameOrEmail(String query) {
        return usersRepository.findUsersByUsernameOrEmail(query);
    }

    @Override
    public Users uploadAvatarByUserId(Long userId, MultipartFile file) {
        Users user = findUserById(userId);
        String avatar = null;
        try {
            avatar = fileService.uploadFileToTelegramChannel(file);
        } catch (IOException e) {
            throw new ResourceNotFoundException("File not found");
        }
        user.getUserAvatars().add(avatar);
        return usersRepository.save(user);
    }

    @Override
    public Users makeAdminById(Long userId) {
        Users user = findUserById(userId);
        user.setRole(Role.ADMIN);
        return usersRepository.save(user);
    }
}
