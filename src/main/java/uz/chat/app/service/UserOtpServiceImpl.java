package uz.chat.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.chat.app.entity.auth.UserOtp;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.exception.InvalidDataException;
import uz.chat.app.repository.UserOtpRepository;
import uz.chat.app.util.MailSender;

import java.time.LocalDateTime;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserOtpServiceImpl implements UserOtpService {

    private final UserOtpRepository userOtpRepository;
    private final UsersService usersService;
    private final MailSender mailSender;

    @Override
    public UserOtp createUserOtp(UserOtp userOtp) {
        return userOtpRepository.save(userOtp);
    }

    @Override
    public UserOtp generateUserOtp(String username) {
        Users user = usersService.findUserByUsername(username);
        UserOtp userOtp = userOtpRepository.findByUser(user).orElse(null);
        if (userOtp != null) {
            //check otp expiry date
            if (userOtp.getOtpExpiryDate().isBefore(LocalDateTime.now())) {
                //delete invalid otp
                userOtpRepository.delete(userOtp);
                //send activation to user
                mailSender.sendActivationCode(user.getUsername(),user.getEmail());
                //create new otp
                return createUserOtp(
                        UserOtp.builder()
                                .user(user)
                                .otp(generateOtp(username))
                                .otpExpiryDate(LocalDateTime.now().plusMinutes(15)).build()
                );
            }else {
                //send activation to user
                mailSender.sendActivationCode(user.getUsername(), user.getEmail());
                return userOtp;
            }
        }
        else {
            //send activation to user
            mailSender.sendActivationCode(user.getUsername(), user.getEmail());
            return createUserOtp(UserOtp.builder()
                    .user(user)
                    .otp(generateOtp(username))
                    .otpExpiryDate(LocalDateTime.now().plusMinutes(15))
                    .build());
        }
    }

    @Override
    public String generateOtp(String username) {
        return Base64.getEncoder().encodeToString(username.getBytes());
    }

    @Override
    public String checkActivationAccount(String token) {
        UserOtp userOtp = userOtpRepository.findByOtp(token).orElseThrow(() -> new InvalidDataException("Invalid token"));
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
