package dev.mohibullah.book_crud_demo.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

////////////////////////////////////////////////////////////////////////////////////////////////
// This DTO class, BaseShowAllResponseDTO, is typically used to represent paginated responses 
// in the application. It includes fields for the content of type T, the current page number, 
// the page size, the total number of elements in the dataset, the total number of pages, 
// and a flag to indicate whether the current page is the last page in the pagination. 
// This allows you to structure and provide paginated data to clients in the application.
////////////////////////////////////////////////////////////////////////////////////////////////

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseShowAllResponseDTO<T> {
    private List<T> content;
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
