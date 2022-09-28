package by.yermak.elibrary.mapper.bookMapper;

import by.yermak.elibrary.mapper.Mapper;
import by.yermak.elibrary.database.entity.book.Author;
import by.yermak.elibrary.dto.bookDto.AuthorReadDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorReadMapper implements Mapper<Author, AuthorReadDto> {

    @Override
    public AuthorReadDto map(Author object) {
        return new AuthorReadDto(
                object.getId(),
                object.getName()
        );
    }
}

