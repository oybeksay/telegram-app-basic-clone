package uz.chat.app.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import uz.chat.app.dto.MessageDto;
import uz.chat.app.entity.Message;
import uz.chat.app.service.MessageService;

@Controller
public class ChatController {

    private final MessageService messageService;

    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/sendMessage/{chatId}")
    @SendTo("/topic/{chatId}")
    public Message sendMessage(@DestinationVariable Long chatId, MessageDto messageDto) {
        return messageService.createMessage(messageDto, chatId);
    }


}
