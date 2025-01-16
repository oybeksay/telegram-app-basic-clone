package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.chat.app.entity.PrivateChat;
import uz.chat.app.service.ChatService;
import uz.chat.app.service.MessageService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class PrivateChatController {

    private final ChatService chatService;
    private final MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<PrivateChat> createPrivateChat(@RequestBody List<Long> usersId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createPrivateChat(usersId));
    }

    @GetMapping("/find/{chatId}")
    public ResponseEntity<PrivateChat> findPrivateChat(@PathVariable Long chatId) {
        return ResponseEntity.ok(chatService.findById(chatId));
    }

    @GetMapping("/find/user/{id}")
    public ResponseEntity<List<PrivateChat>> findPrivateChatByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(chatService.findPrivateChatByUserId(id));
    }


    @DeleteMapping("/delete/{chatId}")
    public ResponseEntity<Void> deletePrivateChat(@PathVariable Long chatId) {
        chatService.deletePrivateChatById(chatId);
        return ResponseEntity.noContent().build();
    }

}
