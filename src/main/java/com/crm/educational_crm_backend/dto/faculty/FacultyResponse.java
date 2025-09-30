package com.crm.educational_crm_backend.dto.faculty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class FacultyResponse {

    private UUID id;
    private UUID userId;
    private String username;
    private String department;
    private String specialization;
    private LocalDate hireDate;
    private String designation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public FacultyResponse() {}

    public FacultyResponse(UUID id, UUID userId, String username, String department, String specialization,
                           LocalDate hireDate, String designation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.department = department;
        this.specialization = specialization;
        this.hireDate = hireDate;
        this.designation = designation;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
