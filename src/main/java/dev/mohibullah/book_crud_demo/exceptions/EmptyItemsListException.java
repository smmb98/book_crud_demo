package dev.mohibullah.book_crud_demo.exceptions;

////////////////////////////////////////////////////////////////////////////////////////////////
// This custom exception class, EmptyItemsListException, extends the standard RuntimeException class. 
// It is used to represent and throw exceptions in the application when a list of 
// items is empty or not found, typically based on the message parameter provided 
// when the exception is created. This allows you to handle and respond to cases 
// where a list of items is expected but none are available.
////////////////////////////////////////////////////////////////////////////////////////////////

public class EmptyItemsListException extends RuntimeException {
    public EmptyItemsListException(String message) {
        super(message);
    }
}