package uz.chat.app.mapper;

import org.mapstruct.Mapper;
import uz.chat.app.dto.UsersDto;
import uz.chat.app.entity.auth.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    Users toEntity(UsersDto usersDto);

}
