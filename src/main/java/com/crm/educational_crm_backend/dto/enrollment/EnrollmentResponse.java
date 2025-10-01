package com.crm.educational_crm_backend.dto.enrollment;

import com.crm.educational_crm_backend.entity.enums.EnrollmentStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class EnrollmentResponse {
    private UUID id;
    private UUID studentId;
    private String studentName;
    private UUID courseId;
    private String courseName;
    private LocalDateTime enrollmentDate;
    private EnrollmentStatus status;

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public UUID getCourseId() { return courseId; }
    public void setCourseId(UUID courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public LocalDateTime getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDateTime enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }
}
