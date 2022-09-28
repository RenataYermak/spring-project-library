package by.yermak.elibrary.dto.userDto;

import by.yermak.elibrary.database.entity.user.Role;

public record UserReadDto(Long id, String firstName, String lastName,
                          String username,  String email,
                          Role role){
}
