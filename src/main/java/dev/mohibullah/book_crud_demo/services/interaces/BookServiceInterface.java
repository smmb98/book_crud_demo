package dev.mohibullah.book_crud_demo.services.interaces;

import dev.mohibullah.book_crud_demo.dtos.requests.BookRequestDTO;
import dev.mohibullah.book_crud_demo.dtos.responses.BaseShowAllResponseDTO;
import dev.mohibullah.book_crud_demo.dtos.responses.BookResponseDTO;

public interface BookServiceInterface {

    BaseShowAllResponseDTO<BookResponseDTO> showBooks(int pageNo, int pageSize);
    BookResponseDTO showBookById(int bookId);
    BookResponseDTO createBook(BookRequestDTO bookRequestDTO);
    BookResponseDTO updateBook(BookRequestDTO bookRequestDTO, int bookId);
    void deleteBook(BookRequestDTO bookRequestDTO, int bookId);

}
