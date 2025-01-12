package uz.chat.app.config.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Xabarlarni yuborish uchun
        config.setApplicationDestinationPrefixes("/app"); // Foydalanuvchi xabar jo'natadigan endpointlar
    }

    @Override
    public void registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry registry) {
        registry.addEndpoint("/chat") // WebSocket ulanadigan endpoint
                .setAllowedOrigins("*") // Har qanday manzildan ulanishga ruxsat
                .withSockJS(); // Agar WebSocket ishlamasa, SockJS alternativani qo‘llaydi
    }

}
