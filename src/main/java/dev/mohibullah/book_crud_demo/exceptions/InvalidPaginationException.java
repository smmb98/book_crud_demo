package dev.mohibullah.book_crud_demo.exceptions;

public class InvalidPaginationException extends RuntimeException {
    public InvalidPaginationException() {
        super("PageNo or PageSize is invalid. It should be a positive integer.");
    }
}