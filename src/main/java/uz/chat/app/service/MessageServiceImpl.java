package uz.chat.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.chat.app.dto.MessageDto;
import uz.chat.app.entity.Message;
import uz.chat.app.exception.ResourceNotFoundException;
import uz.chat.app.mapper.MessageMapper;
import uz.chat.app.repository.MessageRepository;

import java.util.List;

@Slf4j
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

    @Override
    public Message updateMessageById(Long messageId, MessageDto updateMessageDto) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        message.setContent(updateMessageDto.content());
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findMessagesByChatId(Long chatId, Pageable pageable) {
        return messageRepository.findAllByPrivateChat_Id(chatId, pageable);
    }

    @Override
    public void deleteMessageById(Long messageId) {
        log.info("Delete message: {}", messageId);
        messageRepository.deleteById(messageId);
    }
}
