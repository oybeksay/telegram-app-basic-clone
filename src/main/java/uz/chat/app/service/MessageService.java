package uz.chat.app.service;

import uz.chat.app.dto.MessageDto;
import uz.chat.app.entity.Message;

public interface MessageService {

    Message createMessage(MessageDto messageDto, Long chatId);

}
