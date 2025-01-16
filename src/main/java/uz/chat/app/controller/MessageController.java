package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.chat.app.dto.MessageDto;
import uz.chat.app.entity.Message;
import uz.chat.app.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("/find/messages/{chatId}")
    private ResponseEntity<List<Message>> findPrivateChatMessagesByChatId(@PathVariable Long chatId,
                                                                          @RequestParam(defaultValue = "0") Integer page,
                                                                          @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.ok(messageService.findMessagesByChatId(chatId, PageRequest.of(page, size)));
    }

    @PutMapping("/updateMessage/{messageId}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long messageId, @RequestBody MessageDto updateMessageDto) {
        return ResponseEntity.ok(messageService.updateMessageById(messageId, updateMessageDto));
    }

    @DeleteMapping("/deleteMessage/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long messageId) {
        messageService.deleteMessageById(messageId);
        return ResponseEntity.noContent().build();
    }

}

