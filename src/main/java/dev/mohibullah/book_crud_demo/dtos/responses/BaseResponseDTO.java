package dev.mohibullah.book_crud_demo.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

////////////////////////////////////////////////////////////////////////////////////////////////
// This DTO class, BaseResponseDTO, is a common parent for ResponseDTOs in the application and is 
// used for representing responses and includes fields 
// for the creation timestamp (createdAt) and the last update timestamp (updatedAt). 
// The @JsonInclude annotation specifies that fields with empty values should not be 
// included in the JSON serialization. It's commonly used to avoid sending empty fields 
// in the response to reduce unnecessary data transfer.
////////////////////////////////////////////////////////////////////////////////////////////////

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseResponseDTO {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
