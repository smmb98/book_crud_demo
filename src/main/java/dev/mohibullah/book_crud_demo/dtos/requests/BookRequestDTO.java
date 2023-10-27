package dev.mohibullah.book_crud_demo.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

////////////////////////////////////////////////////////////////////////////////////////////////
// This DTO class represents a request body for creating or updating a book in the controller. 
// It includes fields for the book's title, ISBN, publication date, price, and the author's name. 
// Each field has associated validation constraints and error messages to ensure the data meets 
// specific criteria when processing the request.
////////////////////////////////////////////////////////////////////////////////////////////////

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 30, message = "Title should be between 2 and 30 characters")
    private String title;

    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "ISBN should be 10 or 13 digits")
    private String bookISBN;

    @NotNull(message = "Publication date cannot be blank")
    @Past(message = "Publication date must be in the past")
    private LocalDate publicationDate;

    @NotNull(message = "Price cannot be blank")
    private double price;

    @NotBlank(message = "Author's name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z .&]+$", message = "Author's name must consist of alphabetic characters, '.', and '&'")
    @Size(min = 2, max = 30, message = "Author's name should be between 2 and 30 characters")
    private String author;
}
