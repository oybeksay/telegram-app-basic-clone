package uz.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.chat.app.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}