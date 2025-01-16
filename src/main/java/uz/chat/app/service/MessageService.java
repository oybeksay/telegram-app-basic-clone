package uz.chat.app.service;

import org.springframework.data.domain.Pageable;
import uz.chat.app.dto.MessageDto;
import uz.chat.app.entity.Message;

import java.util.List;

public interface MessageService {

    Message createMessage(MessageDto messageDto, Long chatId);

    Message updateMessageById(Long messageId, MessageDto updateMessageDto);

    List<Message> findMessagesByChatId(Long chatId, Pageable pageable);

    void deleteMessageById(Long messageId);

}
