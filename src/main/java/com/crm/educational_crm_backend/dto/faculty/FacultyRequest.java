package com.crm.educational_crm_backend.dto.faculty;

import java.time.LocalDate;
import java.util.UUID;

public class FacultyRequest {

    private UUID userId;
    private String department;
    private String specialization;
    private LocalDate hireDate;
    private String designation;

    // Constructors
    public FacultyRequest() {}

    public FacultyRequest(UUID userId, String department, String specialization, LocalDate hireDate, String designation) {
        this.userId = userId;
        this.department = department;
        this.specialization = specialization;
        this.hireDate = hireDate;
        this.designation = designation;
    }

    // Getters and Setters
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public LocalDate getHireDate() { return hireDate; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
}
