package dev.mohibullah.book_crud_demo.repositories;

import dev.mohibullah.book_crud_demo.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);
}
