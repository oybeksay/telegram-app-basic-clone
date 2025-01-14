package uz.chat.app.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import uz.chat.app.entity.auth.Users;

@AllArgsConstructor
@Data
@Value
public class UserEvent {
    Users user;
}
