package by.yermak.elibrary.database.repository;

import by.yermak.elibrary.database.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {
}
