package uz.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.chat.app.entity.auth.UserOtp;

import java.util.List;
import java.util.Optional;

public interface UserOtpRepository extends JpaRepository<UserOtp, Long> {
    Optional<UserOtp> findByOtp(String otp);

    void deleteByOtp(String otp);
}