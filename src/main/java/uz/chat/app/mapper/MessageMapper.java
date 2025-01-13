package uz.chat.app.mapper;

import org.mapstruct.Mapper;
import uz.chat.app.dto.MessageDto;
import uz.chat.app.entity.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    Message toEntity(MessageDto messageDto);

}
