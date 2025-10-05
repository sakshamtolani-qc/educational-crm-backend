package com.crm.educational_crm_backend.entity.admissions;

import com.crm.educational_crm_backend.entity.enums.AdmissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "admissions")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Admission {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "lead_id")
    private UUID leadId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "course_id")
    private UUID courseId;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "application_date")
    private LocalDateTime applicationDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AdmissionStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "documents", columnDefinition = "text")
    private String documents; // store JSON as string
}
