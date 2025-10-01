package com.crm.educational_crm_backend.dto.enrollment;

import com.crm.educational_crm_backend.entity.enums.EnrollmentStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public class EnrollmentRequest {
    private UUID studentId;
    private UUID courseId;
    private LocalDateTime enrollmentDate; // optional
    private EnrollmentStatus status;      // optional

    // Getters and Setters
    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }

    public UUID getCourseId() { return courseId; }
    public void setCourseId(UUID courseId) { this.courseId = courseId; }

    public LocalDateTime getEnrollmentDate() { return enrollmentDate; }
    public void setEnrollmentDate(LocalDateTime enrollmentDate) { this.enrollmentDate = enrollmentDate; }

    public EnrollmentStatus getStatus() { return status; }
    public void setStatus(EnrollmentStatus status) { this.status = status; }
}
