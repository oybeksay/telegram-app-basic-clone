package uz.chat.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.chat.app.entity.auth.UserOtp;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.exception.InvalidDataException;
import uz.chat.app.repository.UserOtpRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserOtpServiceImpl implements UserOtpService {

    private final UserOtpRepository userOtpRepository;
    private final UsersService usersService;

    @Override
    public UserOtp createUserOtp(UserOtp userOtp) {
        return userOtpRepository.save(userOtp);
    }

    @Override
    public String checkActivationAccount(String token) {
        String username = new String(Base64.getDecoder().decode(token));
        UserOtp userOtp = userOtpRepository.findByOtp(username).orElseThrow(() -> new InvalidDataException("Invalid token"));
        if (userOtp.getOtpExpiryDate().isBefore(LocalDateTime.now())) {
            throw new InvalidDataException("Invalid token");
        }else {
            Users user = userOtp.getUser();
            //user activated
            usersService.updateUserEnabled(user.getId(), true);
            //delete otp from database
            deleteUserOtpByOtp(userOtp.getOtp());
        }

        return "Success";
    }

    @Override
    public void deleteUserOtpByOtp(String otp) {
        userOtpRepository.deleteByOtp(otp);
    }


}
