package com.crm.educational_crm_backend.dto.subject;

import java.time.LocalDateTime;
import java.util.UUID;

public class SubjectResponse {
    private UUID id;
    private UUID courseId;
    private UUID facultyId;
    private String name;
    private String code;
    private int credits;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructor
    public SubjectResponse(UUID id, UUID courseId, UUID facultyId, String name, String code, int credits,
                           LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.courseId = courseId;
        this.facultyId = facultyId;
        this.name = name;
        this.code = code;
        this.credits = credits;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters
    public UUID getId() { return id; }
    public UUID getCourseId() { return courseId; }
    public UUID getFacultyId() { return facultyId; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public int getCredits() { return credits; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
