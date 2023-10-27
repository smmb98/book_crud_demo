package dev.mohibullah.book_crud_demo.services.interaces;

import dev.mohibullah.book_crud_demo.dtos.requests.BookRequestDTO;
import dev.mohibullah.book_crud_demo.dtos.responses.BaseShowAllResponseDTO;
import dev.mohibullah.book_crud_demo.dtos.responses.BookResponseDTO;

////////////////////////////////////////////////////////////////////////////////////////////////
// This interface defines the contract for book-related operations within the Spring Boot application. 
// It specifies methods for showing a list of books with pagination, retrieving a book by its ID, 
// creating a new book, updating an existing book, and deleting a book. 
// Implementing classes should provide the logic for these methods.
////////////////////////////////////////////////////////////////////////////////////////////////

public interface BookServiceInterface {

    // Retrieve a list of books with pagination.
    BaseShowAllResponseDTO<BookResponseDTO> showBooks(int pageNo, int pageSize);

    // Retrieve a book by its unique ID.
    BookResponseDTO showBookById(int bookId);

    // Create a new book based on the provided book request data.
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);

    // Update an existing book with the provided book request data.
    BookResponseDTO updateBook(BookRequestDTO bookRequestDTO, int bookId);

    // Delete a book by its unique ID.
    void deleteBook(int bookId);
}
