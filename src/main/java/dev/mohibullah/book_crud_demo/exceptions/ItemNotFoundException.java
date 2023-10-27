package dev.mohibullah.book_crud_demo.exceptions;

////////////////////////////////////////////////////////////////////////////////////////////////
// This custom exception class, ItemNotFoundException, extends the standard RuntimeException class. 
// It is used to represent and throw exceptions in the application when an item, 
// typically referred to in the message parameter, is not found. It allows you to handle and 
// respond to such exceptions in a customized way within the application.
////////////////////////////////////////////////////////////////////////////////////////////////

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
