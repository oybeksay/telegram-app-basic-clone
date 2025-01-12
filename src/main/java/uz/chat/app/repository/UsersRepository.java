package uz.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.chat.app.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByUsername(String username);
}