package com.crm.educational_crm_backend.entity.exam;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import com.crm.educational_crm_backend.entity.enums.ExamStatus;
import com.crm.educational_crm_backend.entity.enums.ExamType;

@Entity
@Table(name = "exams")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exam {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private UUID courseId;

    @Column(nullable = false)
    private UUID subjectId;

    @Column(nullable = false)
    private LocalDateTime examDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamType type; // MIDTERM, FINAL, QUIZ

    @Column(nullable = false)
    private Double maxMarks;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExamStatus status; // SCHEDULED, COMPLETED, CANCELLED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        status = ExamStatus.SCHEDULED;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
