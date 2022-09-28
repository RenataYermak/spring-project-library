package by.yermak.elibrary.dto.bookDto;

import by.yermak.elibrary.database.entity.book.Category;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

@FieldNameConstants
public record BookCreateDto(String title, Integer authorId,
                            Category category, int publishYear,
                            String description, int number, MultipartFile image) {
}
