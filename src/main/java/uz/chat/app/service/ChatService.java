package uz.chat.app.service;

import uz.chat.app.entity.PrivateChat;
import uz.chat.app.entity.Users;

import java.util.List;

public interface ChatService {

    PrivateChat createPrivateChat(List<Long> usersId);

    PrivateChat findById(Long chatId);
}
