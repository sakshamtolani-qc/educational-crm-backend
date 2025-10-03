package com.crm.educational_crm_backend.dto.attendance;

import java.time.LocalDate;
import java.util.UUID;

public class AttendanceRequest {
    private UUID studentId;
    private UUID subjectId;
    private LocalDate attendanceDate;
    private String status;

    public AttendanceRequest() {}

    public AttendanceRequest(UUID studentId, UUID subjectId, LocalDate attendanceDate, String status) {
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    // Getters & Setters
    public UUID getStudentId() { return studentId; }
    public void setStudentId(UUID studentId) { this.studentId = studentId; }

    public UUID getSubjectId() { return subjectId; }
    public void setSubjectId(UUID subjectId) { this.subjectId = subjectId; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
