package by.yermak.elibrary.dto.bookDto;

import by.yermak.elibrary.database.entity.book.Category;

public record BookReadDto(Long id, String title, Category category,
                          int publishYear, String description, int number,
                          AuthorReadDto author, String image) {
}
