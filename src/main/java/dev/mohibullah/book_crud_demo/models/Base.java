package dev.mohibullah.book_crud_demo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

////////////////////////////////////////////////////////////////////////////////////////////////
// This base class is a common parent for entities in the application. 
// It provides fields for 'createdAt' and 'updatedAt' timestamps, and two callback methods. 
// The onCreate method sets the timestamps when a new entity is persisted,
// and the onUpdate method updates the 'updatedAt' timestamp when an entity is modified. 
// This class is typically used to avoid duplicating timestamp-related fields 
// and logic in multiple entity classes
////////////////////////////////////////////////////////////////////////////////////////////////

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class Base {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // This method is called before persisting a new entity.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // This method is called before updating an existing entity.
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}