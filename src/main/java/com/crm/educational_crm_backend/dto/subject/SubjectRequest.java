package com.crm.educational_crm_backend.dto.subject;

import java.util.UUID;

public class SubjectRequest {
    private UUID courseId;
    private UUID facultyId;
    private String name;
    private String code;
    private int credits;

    // Getters & Setters
    public UUID getCourseId() { return courseId; }
    public void setCourseId(UUID courseId) { this.courseId = courseId; }
    public UUID getFacultyId() { return facultyId; }
    public void setFacultyId(UUID facultyId) { this.facultyId = facultyId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }
}
