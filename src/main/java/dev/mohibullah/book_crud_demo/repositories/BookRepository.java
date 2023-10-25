package dev.mohibullah.book_crud_demo.repositories;

import dev.mohibullah.book_crud_demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {
}
