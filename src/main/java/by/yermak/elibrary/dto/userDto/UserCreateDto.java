package by.yermak.elibrary.dto.userDto;

import by.yermak.elibrary.database.entity.user.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Email;


@Value
@FieldNameConstants

public class UserCreateDto {
    String firstName;

    String lastName;

    String username;

    String rawPassword;

    @Email
    String email;

    Role role;
}
