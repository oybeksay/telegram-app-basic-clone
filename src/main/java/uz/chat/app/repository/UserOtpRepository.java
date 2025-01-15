package uz.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import uz.chat.app.entity.auth.UserOtp;
import uz.chat.app.entity.auth.Users;

import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {
    Optional<UserOtp> findByOtp(String otp);

    @Transactional
    void deleteByOtp(String otp);

    Optional<UserOtp> findByUser(Users user);
}