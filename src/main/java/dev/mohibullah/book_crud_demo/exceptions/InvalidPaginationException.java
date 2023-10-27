package dev.mohibullah.book_crud_demo.exceptions;

////////////////////////////////////////////////////////////////////////////////////////////////
// This custom exception class, InvalidPaginationException, extends the standard RuntimeException class. 
// It is used to represent and throw exceptions in the application when pagination parameters 
// (pageNo or pageSize) are invalid, typically because they are not positive integers. 
// The exception includes a message that describes the nature of the problem, 
// making it easier to understand the cause of the exception.
////////////////////////////////////////////////////////////////////////////////////////////////

public class InvalidPaginationException extends RuntimeException {
    public InvalidPaginationException() {
        super("PageNo or PageSize is invalid. It should be a positive integer.");
    }
}