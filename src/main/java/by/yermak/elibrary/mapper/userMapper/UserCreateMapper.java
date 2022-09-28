package by.yermak.elibrary.mapper.userMapper;

import by.yermak.elibrary.mapper.Mapper;
import by.yermak.elibrary.dto.userDto.UserCreateDto;
import by.yermak.elibrary.database.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements Mapper<UserCreateDto, User> {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserCreateDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateDto object) {
        User user = new User();
        copy(object, user);

        return user;
    }

    private void copy(UserCreateDto object, User user) {
        user.setLastName(object.getLastName());
        user.setFirstName(object.getFirstName());
        user.setUsername(object.getUsername());
        user.setEmail(object.getEmail());
        user.setRole(object.getRole());

        Optional.ofNullable(object.getRawPassword())
                .filter(StringUtils::hasText)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);
    }
}