package dev.mohibullah.book_crud_demo.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import dev.mohibullah.book_crud_demo.models.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private Author author;
}
