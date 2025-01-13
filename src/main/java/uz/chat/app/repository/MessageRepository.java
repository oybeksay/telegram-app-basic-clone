package uz.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.chat.app.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}