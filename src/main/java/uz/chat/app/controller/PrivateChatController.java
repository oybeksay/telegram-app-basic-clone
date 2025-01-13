package uz.chat.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.chat.app.entity.PrivateChat;
import uz.chat.app.service.ChatService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class PrivateChatController {

    private final ChatService chatService;

    @PostMapping("/create")
    public ResponseEntity<PrivateChat> createPrivateChat(@RequestBody List<Long> usersId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(chatService.createPrivateChat(usersId));
    }

}
