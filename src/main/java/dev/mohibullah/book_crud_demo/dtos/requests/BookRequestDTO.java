package dev.mohibullah.book_crud_demo.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDTO {
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 2, max = 30, message = "Title should be between 2 and 30 characters")
    private String title;

    @NotBlank(message = "ISBN cannot be blank")
    @Pattern(
            regexp = "^(\\d{10}|\\d{13})$",
            message = "ISBN should be 10 or 13 digits"
    )
    private String ISBN;

    @NotBlank(message = "Publication date cannot be blank")
    private LocalDate publication_date;

    @NotBlank(message = "Price cannot be blank")
    private double price;

    @NotBlank(message = "Author's name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Author's name must consist only of alphabetic characters.")
    @Size(min = 2, max = 30, message = "Author's name should be between 2 and 30 characters")
    private String author;
}
