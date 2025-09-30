package com.crm.educational_crm_backend.dto.student;

import java.time.LocalDate;
import java.util.UUID;

public class StudentResponse {

    private UUID id;
    private UUID userId;
    private String enrollmentNo;
    private LocalDate dob;
    private String address;
    private LocalDate admissionDate;
    private LocalDate enrollmentDate;

    public StudentResponse(UUID id, UUID userId, String enrollmentNo, LocalDate dob,
                           String address, LocalDate admissionDate, LocalDate enrollmentDate) {
        this.id = id;
        this.userId = userId;
        this.enrollmentNo = enrollmentNo;
        this.dob = dob;
        this.address = address;
        this.admissionDate = admissionDate;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEnrollmentNo() {
        return enrollmentNo;
    }

    public void setEnrollmentNo(String enrollmentNo) {
        this.enrollmentNo = enrollmentNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}
