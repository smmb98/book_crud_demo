package dev.mohibullah.book_crud_demo.exceptions;

public class EmptyItemsListException extends RuntimeException {
    public EmptyItemsListException(String message) {
        super(message);
    }
}