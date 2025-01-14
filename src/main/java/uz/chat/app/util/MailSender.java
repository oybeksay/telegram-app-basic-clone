package uz.chat.app.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailSender {

    private final String baseUrl = "http://localhost:8080/activation/";

    private final JavaMailSender javaMailSender;
    private final Configuration configuration;

    @Async
    public void sendActivationCode(String username, String email){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom("telegram.clone.project@gmail.com");
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject("Activation your account");
            Template template = configuration.getTemplate("activation_account.ftlh");
            String token = Base64.getEncoder().encodeToString(username.getBytes());
            // set user data
            Map<String, String> objectModel = Map.of("username", username, "url", baseUrl.concat(token));
            String htmlMailContent = FreeMarkerTemplateUtils.processTemplateIntoString(template, objectModel);
            mimeMessageHelper.setText(htmlMailContent, true);
            //send
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}
