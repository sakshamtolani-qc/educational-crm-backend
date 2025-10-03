package com.crm.educational_crm_backend.entity.results;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "results")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private UUID examId;

    @Column(nullable = false)
    private UUID studentId;

    @Column(nullable = false)
    private Double marksObtained;

    @Column(nullable = true)
    private Integer remarks;// initially null, can be updated later when Re-evaluation

    @Column(nullable = false)
    private String grade; // Calculated in service

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
