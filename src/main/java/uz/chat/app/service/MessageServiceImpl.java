package uz.chat.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import uz.chat.app.dto.MessageDto;
import uz.chat.app.entity.Message;
import uz.chat.app.mapper.MessageMapper;
import uz.chat.app.repository.MessageRepository;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;
    private final ChatService chatService;

    @Override
    public Message createMessage(MessageDto messageDto, Long chatId) {
        Message message = messageMapper.toEntity(messageDto);
        message.setPrivateChat(chatService.findById(chatId));
        return messageRepository.save(message);
    }
}
