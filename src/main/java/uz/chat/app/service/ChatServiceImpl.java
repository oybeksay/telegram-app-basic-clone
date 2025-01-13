package uz.chat.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.chat.app.entity.PrivateChat;
import uz.chat.app.exception.ResourceNotFoundException;
import uz.chat.app.repository.PrivateChatRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final UsersService usersService;
    private final PrivateChatRepository privateChatRepository;

    @Override
    public PrivateChat createPrivateChat(List<Long> usersId) {
        PrivateChat chat = PrivateChat
                .builder()
                .users(usersId.stream().map(usersService::findUserById).collect(Collectors.toList()))
                .build();
        return privateChatRepository.save(chat);
    }

    @Override
    public PrivateChat findById(Long chatId) {
        return privateChatRepository.findById(chatId).orElseThrow(() -> new ResourceNotFoundException("Chat not found"));
    }
}
