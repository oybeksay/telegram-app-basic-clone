package uz.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.chat.app.entity.PrivateChat;

public interface PrivateChatRepository extends JpaRepository<PrivateChat, Long> {
}