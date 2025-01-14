package uz.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.chat.app.entity.auth.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("update Users u set u.enabled = ?1 where u.id = ?2")
    void updateEnabledById(boolean enabled, Long id);
}