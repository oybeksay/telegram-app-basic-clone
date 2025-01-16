package uz.chat.app.service;

import uz.chat.app.entity.Message;
import uz.chat.app.entity.PrivateChat;

import java.util.List;

public interface ChatService {

    PrivateChat createPrivateChat(List<Long> usersId);

    PrivateChat findById(Long chatId);

    List<PrivateChat> findPrivateChatByUserId(Long id);

    void deletePrivateChatById(Long chatId);
}
