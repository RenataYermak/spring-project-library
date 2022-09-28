package by.yermak.elibrary.mapper.bookMapper;

import by.yermak.elibrary.mapper.Mapper;
import by.yermak.elibrary.database.entity.book.Book;
import by.yermak.elibrary.dto.bookDto.AuthorReadDto;
import by.yermak.elibrary.dto.bookDto.BookReadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookReadMapper implements Mapper<Book, BookReadDto> {

    private final AuthorReadMapper authorReadMapper;

    @Override
    public BookReadDto map(Book object) {
        AuthorReadDto author = Optional.ofNullable(object.getAuthor())
                .map(authorReadMapper::map)
                .orElse(null);
        return new BookReadDto(
                object.getId(),
                object.getTitle(),
                object.getCategory(),
                object.getPublishYear(),
                object.getDescription(),
                object.getNumber(),
                author,
                object.getImage()
        );
    }

}
