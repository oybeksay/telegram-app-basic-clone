package uz.chat.app.service;

import uz.chat.app.entity.auth.UserOtp;

public interface UserOtpService {

    UserOtp createUserOtp(UserOtp userOtp);

    String checkActivationAccount(String token);

    void deleteUserOtpByOtp(String otp);

}
