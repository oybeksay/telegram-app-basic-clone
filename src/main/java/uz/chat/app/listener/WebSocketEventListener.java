package uz.chat.app.listener;

import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class WebSocketEventListener {
    private final RedisTemplate<String, String> redisTemplate;
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketEventListener(RedisTemplate<String, String> redisTemplate, SimpMessagingTemplate messagingTemplate) {
        this.redisTemplate = redisTemplate;
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        String userId = getUserIdFromEvent(event);
        redisTemplate.opsForValue().set("user:" + userId + ":status", "online");
        messagingTemplate.convertAndSend("/topic/userStatus/" + userId, "online");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        String userId = getUserIdFromEvent(event);
        redisTemplate.opsForValue().set("user:" + userId + ":status", "offline");
        messagingTemplate.convertAndSend("/topic/userStatus/" + userId, "offline");
    }

    private String getUserIdFromEvent(AbstractSubProtocolEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        return Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("userId").toString();
    }
}
