package by.yermak.elibrary.database.repository;

import by.yermak.elibrary.database.entity.book.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
