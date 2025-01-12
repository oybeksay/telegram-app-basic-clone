package uz.chat.app.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.chat.app.entity.Users;
import uz.chat.app.repository.UsersRepository;

@Component
public class CustomUsersDetailsService implements UserDetailsService {


    private final UsersRepository usersRepository;

    public CustomUsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findUserByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("User not found : " + username));

        return new CustomUsersDetails(user);
    }
}
