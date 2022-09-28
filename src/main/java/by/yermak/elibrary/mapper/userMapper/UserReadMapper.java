package by.yermak.elibrary.mapper.userMapper;

import by.yermak.elibrary.mapper.Mapper;
import by.yermak.elibrary.dto.userDto.UserReadDto;
import by.yermak.elibrary.database.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getLastName(),
                object.getFirstName(),
                object.getUsername(),
                object.getEmail(),
                object.getRole()
        );
    }
}
