package uz.chat.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.chat.app.entity.PrivateChat;

import java.util.List;
import java.util.Optional;

public interface PrivateChatRepository extends JpaRepository<PrivateChat, Long> {
    List<PrivateChat> findByUsers_Id(Long id);
}