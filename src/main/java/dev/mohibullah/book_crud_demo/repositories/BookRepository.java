package dev.mohibullah.book_crud_demo.repositories;

import dev.mohibullah.book_crud_demo.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

////////////////////////////////////////////////////////////////////////////////////////////////
// This repository interface extends JpaRepository and provides standard CRUD 
// (Create, Read, Update, Delete) operations for the Book entity in the database. 
// It allows you to interact with the database to perform these operations on books.
////////////////////////////////////////////////////////////////////////////////////////////////

public interface BookRepository extends JpaRepository<Book, Integer> {
}
