package uz.chat.app.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TelegramSender {

    @Value("${telegram.sender.botToken}")
    private String botToken;

    @Value("${telegram.sender.chatId}")
    private String chatId;

}
