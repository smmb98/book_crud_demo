package dev.mohibullah.book_crud_demo.services.implementations;

import dev.mohibullah.book_crud_demo.dtos.requests.BookRequestDTO;
import dev.mohibullah.book_crud_demo.dtos.responses.BaseShowAllResponseDTO;
import dev.mohibullah.book_crud_demo.dtos.responses.BookResponseDTO;
import dev.mohibullah.book_crud_demo.exceptions.EmptyItemsListException;
import dev.mohibullah.book_crud_demo.exceptions.ItemNotFoundException;
import dev.mohibullah.book_crud_demo.models.Author;
import dev.mohibullah.book_crud_demo.models.Book;
import dev.mohibullah.book_crud_demo.repositories.AuthorRepository;
import dev.mohibullah.book_crud_demo.repositories.BookRepository;
import dev.mohibullah.book_crud_demo.services.interaces.BookServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImplementation implements BookServiceInterface {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BaseShowAllResponseDTO<BookResponseDTO> showBooks(int pageNo, int pageSize) {
        if (pageSize == 0) {
            List<Book> allBooks = bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
            if (allBooks.isEmpty()) {
                throw new EmptyItemsListException("No Books in database");
            }

            List<BookResponseDTO> content = allBooks.stream()
                    .map(book -> mapToResponseDto(book))
                    .toList();

            BaseShowAllResponseDTO<BookResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(0);
            response.setPageSize(allBooks.size());
            response.setTotalElements(allBooks.size());
            response.setTotalPages(1);
            response.setLast(true);

            return response;
        } else {
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.ASC, "id"));
            Page<Book> books = bookRepository.findAll(pageable);

            if (books.getTotalElements() == 0) {
                throw new EmptyItemsListException("No Books in database");
            }

            if (books.isEmpty()) {
                throw new EmptyItemsListException("No Book in pageNo: " + books.getNumber());
            }

            List<Book> listOfBooks = books.getContent();
            List<BookResponseDTO> content = listOfBooks.stream()
                    .map(book -> mapToResponseDto(book))
                    .toList();

            BaseShowAllResponseDTO<BookResponseDTO> response = new BaseShowAllResponseDTO<>();
            response.setContent(content);
            response.setPage(books.getNumber());
            response.setPageSize(books.getSize());
            response.setTotalElements(books.getTotalElements());
            response.setTotalPages(books.getTotalPages());
            response.setLast(books.isLast());

            return response;
        }
    }

    @Override
    public BookResponseDTO showBookById(int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book could not be found"));
        return mapToResponseDto(book);
    }

    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {

        Book book = bookRepository.save(mapToEntity(bookRequestDTO));

        return mapToResponseDto(book);
    }

    @Override
    public BookResponseDTO updateBook(BookRequestDTO bookRequestDTO, int bookId) {
        Book book = bookRepository.save(mapToEntity(bookRequestDTO, bookId));

        return mapToResponseDto(book);
    }

    @Override
    public void deleteBook(BookRequestDTO bookRequestDTO, int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book could not be found"));
        bookRepository.delete(book);
    }


    private BookResponseDTO mapToResponseDto(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();

        bookResponseDTO.setAuthor(book.getAuthor());
        bookResponseDTO.setISBN(book.getISBN());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setPublication_date(book.getPublication_date());
        bookResponseDTO.setCreatedAt(book.getCreatedAt());
        bookResponseDTO.setUpdatedAt(book.getUpdatedAt());

        return bookResponseDTO;
    }

    private Book mapToEntity(BookRequestDTO bookRequestDTO) {
        Author author = authorRepository.findByName(bookRequestDTO.getAuthor());

        if (author == null) {
            author = new Author();
            author.setName(bookRequestDTO.getAuthor());
            authorRepository.save(author);
        }

        Book book = new Book();
        book.setISBN(bookRequestDTO.getISBN());
        book.setPrice(bookRequestDTO.getPrice());
        book.setTitle(bookRequestDTO.getTitle());
        book.setPublication_date(bookRequestDTO.getPublication_date());
        book.setAuthor(author);

        return book;
    }

    private Book mapToEntity(BookRequestDTO bookRequestDTO, int bookId) {
        Author author = authorRepository.findByName(bookRequestDTO.getAuthor());

        if (author == null) {
            author = new Author();
            author.setName(bookRequestDTO.getAuthor());
            authorRepository.save(author);
        }

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book could not be found"));

        book.setISBN(bookRequestDTO.getISBN());
        book.setPrice(bookRequestDTO.getPrice());
        book.setTitle(bookRequestDTO.getTitle());
        book.setPublication_date(bookRequestDTO.getPublication_date());
        book.setAuthor(author);

        return book;
    }
}
