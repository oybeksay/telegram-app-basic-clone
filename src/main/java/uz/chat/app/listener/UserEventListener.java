package uz.chat.app.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import uz.chat.app.entity.auth.UserOtp;
import uz.chat.app.entity.auth.Users;
import uz.chat.app.event.UserEvent;
import uz.chat.app.service.UserOtpService;
import uz.chat.app.util.MailSender;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final UserOtpService userOtpService;
    private final MailSender mailSender;

    @EventListener
    public void handleUserEvent(UserEvent userEvent) {
        log.info("handleUserEvent: {}" , userEvent);
        //get user
        Users user = userEvent.getUser();
        //send activation
        mailSender.sendActivationCode(user.getUsername(), user.getEmail());

        UserOtp userOtp = UserOtp.builder()
                .otp(userOtpService.generateOtp(user.getUsername()))
                .user(user)
                .otpExpiryDate(LocalDateTime.now().plusMinutes(15))
                .build();

        //create new otp
        userOtpService.createUserOtp(userOtp);
        //send to user email
        log.info("create new user otp: {}", userOtp);
    }

}
