package dev.mohibullah.book_crud_demo.repositories;

import dev.mohibullah.book_crud_demo.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

////////////////////////////////////////////////////////////////////////////////////////////////
// This repository interface extends JpaRepository and specifies a method for finding 
// an author by their name. It allows you to perform CRUD (Create, Read, Update, Delete) 
// operations on the Author entity in the database, with a specific method for finding authors by name.
////////////////////////////////////////////////////////////////////////////////////////////////

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // Find an author by their name.
    Author findByName(String name);
}
