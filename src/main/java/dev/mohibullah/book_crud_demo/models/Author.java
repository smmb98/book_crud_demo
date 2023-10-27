package dev.mohibullah.book_crud_demo.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

////////////////////////////////////////////////////////////////////////////////////////////////
// This entity class represents an "Author" in the application. 
// It includes fields for the author's ID, name, and a list of associated books. 
// It inherits timestamp fields 'createdAt' and 'updatedAt' from the Base class.
// The @JsonInclude annotation excludes properties with empty values from JSON serialization.
////////////////////////////////////////////////////////////////////////////////////////////////

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author extends Base{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Book> books;
}
