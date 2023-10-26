package dev.mohibullah.book_crud_demo.controllers;

import dev.mohibullah.book_crud_demo.dtos.requests.BookRequestDTO;
import dev.mohibullah.book_crud_demo.dtos.responses.BaseShowAllResponseDTO;
import dev.mohibullah.book_crud_demo.dtos.responses.BookResponseDTO;
import dev.mohibullah.book_crud_demo.exceptions.InvalidPaginationException;
import dev.mohibullah.book_crud_demo.services.implementations.BookServiceImplementation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {
    private final BookServiceImplementation bookServiceImplementation;

    @GetMapping("books")
    public ResponseEntity<BaseShowAllResponseDTO<BookResponseDTO>> showBooks(
            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {

        if (pageNo < 0 || pageSize < 0) {
            throw new InvalidPaginationException();
        }


        return new ResponseEntity<>(
                bookServiceImplementation.showBooks(pageNo, pageSize),
                HttpStatus.OK);
    }

    @GetMapping("books/{id}")
    public ResponseEntity<BookResponseDTO> showBookById(@PathVariable("id") int bookId) {


        return new ResponseEntity<>(
                bookServiceImplementation.showBookById(bookId),
                HttpStatus.OK);
    }

    @PostMapping("books")
    public ResponseEntity<BookResponseDTO> createBook(
            @Valid @RequestBody BookRequestDTO bookRequestDTO) {

        return new ResponseEntity<>(
                bookServiceImplementation.createBook(bookRequestDTO),
                HttpStatus.CREATED);
    }


    @PutMapping("books/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(
            @Valid @RequestBody BookRequestDTO bookRequestDTO,
            @PathVariable("id") int bookId) {

        return new ResponseEntity<>(
                bookServiceImplementation.updateBook(bookRequestDTO, bookId),
                HttpStatus.CREATED);
    }

    @DeleteMapping("books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") int bookId) {

        bookServiceImplementation.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
