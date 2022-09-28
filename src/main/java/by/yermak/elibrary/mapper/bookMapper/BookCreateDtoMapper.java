package by.yermak.elibrary.mapper.bookMapper;

import by.yermak.elibrary.mapper.Mapper;
import by.yermak.elibrary.database.entity.book.Author;
import by.yermak.elibrary.database.entity.book.Book;
import by.yermak.elibrary.dto.bookDto.BookCreateDto;
import by.yermak.elibrary.database.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static java.util.function.Predicate.not;

@Component
@RequiredArgsConstructor
public class BookCreateDtoMapper implements Mapper<BookCreateDto, Book> {

    private final AuthorRepository authorRepository;

    @Override
    public Book map(BookCreateDto fromObject, Book toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Book map(BookCreateDto object) {
        Book book = new Book();
        copy(object, book);
        return book;
    }

    private void copy(BookCreateDto object, Book book) {
        book.setTitle(object.title());
        book.setCategory(object.category());
        book.setPublishYear(object.publishYear());
        book.setDescription(object.description());
        book.setNumber(object.number());
        book.setAuthor(getAuthor(object.authorId()));

        Optional.ofNullable(object.image())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> book.setImage(image.getOriginalFilename()));
    }

    public Author getAuthor(Integer authorId) {
        return Optional.ofNullable(authorId)
                .flatMap(authorRepository::findById)
                .orElse(null);
    }
}
