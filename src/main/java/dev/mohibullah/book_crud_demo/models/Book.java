package dev.mohibullah.book_crud_demo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

////////////////////////////////////////////////////////////////////////////////////////////////
// This entity class represents a "Book" in the application. It includes fields for the 
// book's ID, title, ISBN, publication date, price, and a reference to the associated author. 
// It inherits timestamp fields 'createdAt' and 'updatedAt' from the Base class.
// The @JsonInclude annotation excludes properties with empty values from JSON serialization.
////////////////////////////////////////////////////////////////////////////////////////////////

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String ISBN;
    @Column(nullable = false)
    private LocalDate publication_date;
    @Column(nullable = false)
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
