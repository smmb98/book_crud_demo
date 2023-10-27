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

////////////////////////////////////////////////////////////////////////////////////////////////
// This class is a Spring service that implements the BookServiceInterface. 
// It provides methods for handling book-related operations, including retrieving books 
// (with and without pagination), retrieving a book by its ID, creating a new book, 
// updating an existing book, and deleting a book. Additionally, it contains helper methods 
// for mapping between entities and data transfer objects.
////////////////////////////////////////////////////////////////////////////////////////////////

@Service
@RequiredArgsConstructor
public class BookServiceImplementation implements BookServiceInterface {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    // Retrieve a list of books with optional pagination.
    // Handles both all books retrieval and paginated results.
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

    // Retrieve a book by its unique ID.
    @Override
    public BookResponseDTO showBookById(int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book could not be found"));
        return mapToResponseDto(book);
    }
    
    // Create a new book based on the provided book request data.
    @Override
    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {

        Book book = bookRepository.save(mapToEntity(bookRequestDTO));

        return mapToResponseDto(book);
    }

    // Update an existing book with the provided book request data.
    @Override
    public BookResponseDTO updateBook(BookRequestDTO bookRequestDTO, int bookId) {
        Book book = bookRepository.save(mapToEntity(bookRequestDTO, bookId));

        return mapToResponseDto(book);
    }
     
    // Delete a book by its unique ID.
    @Override
    public void deleteBook(int bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book could not be found"));
        bookRepository.delete(book);
    }

    // Helper method to map a Book entity to a BookResponseDTO.
    // Maps a Book entity to a data transfer object for responses.
    private BookResponseDTO mapToResponseDto(Book book) {
        BookResponseDTO bookResponseDTO = new BookResponseDTO();

        bookResponseDTO.setId(book.getId());
        bookResponseDTO.setAuthor(book.getAuthor().getName());
        bookResponseDTO.setISBN(book.getISBN());
        bookResponseDTO.setPrice(book.getPrice());
        bookResponseDTO.setTitle(book.getTitle());
        bookResponseDTO.setPublication_date(book.getPublication_date());
        bookResponseDTO.setCreatedAt(book.getCreatedAt());
        bookResponseDTO.setUpdatedAt(book.getUpdatedAt());

        return bookResponseDTO;
    }

    // Helper method to map a BookRequestDTO to a Book entity.
    // Maps a data transfer object to a Book entity for creation.
    private Book mapToEntity(BookRequestDTO bookRequestDTO) {
        Author author = authorRepository.findByName(bookRequestDTO.getAuthor());

        if (author == null) {
            author = new Author();
            author.setName(bookRequestDTO.getAuthor());
            authorRepository.save(author);
        }

        Book book = new Book();
        book.setISBN(bookRequestDTO.getBookISBN());
        book.setPrice(bookRequestDTO.getPrice());
        book.setTitle(bookRequestDTO.getTitle());
        book.setPublication_date(bookRequestDTO.getPublicationDate());
        book.setAuthor(author);

        return book;
    }

    // Helper method to map a BookRequestDTO to an existing Book entity for update.
    // Maps a data transfer object to an existing Book entity for updating.
    private Book mapToEntity(BookRequestDTO bookRequestDTO, int bookId) {
        Author author = authorRepository.findByName(bookRequestDTO.getAuthor());

        if (author == null) {
            author = new Author();
            author.setName(bookRequestDTO.getAuthor());
            authorRepository.save(author);
        }

        Book book = bookRepository.findById(bookId).orElseThrow(() -> new ItemNotFoundException("Book could not be found"));

        book.setISBN(bookRequestDTO.getBookISBN());
        book.setPrice(bookRequestDTO.getPrice());
        book.setTitle(bookRequestDTO.getTitle());
        book.setPublication_date(bookRequestDTO.getPublicationDate());
        book.setAuthor(author);

        return book;
    }
}
