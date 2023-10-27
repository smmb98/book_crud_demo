package dev.mohibullah.book_crud_demo.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.book_crud_demo.models.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

////////////////////////////////////////////////////////////////////////////////////////////////
// This DTO class, BookResponseDTO, extends the BaseResponseDTO class and is used for 
// representing responses related to books in your application. It includes fields for the 
// book's unique identifier id, title, ISBN, publication date, price, and the name of the author. 
// The @JsonInclude annotation specifies that fields with empty values should not be included 
// in the JSON serialization, and the @EqualsAndHashCode annotation generates equals 
// and hashCode methods that take the superclass fields into account.
////////////////////////////////////////////////////////////////////////////////////////////////

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BookResponseDTO  extends BaseResponseDTO{
    private int id;
    private String title;
    private String ISBN;
    private LocalDate publication_date;
    private double price;
    private String author;
}
