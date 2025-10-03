package com.crm.educational_crm_backend.dto.enrollment;

import com.crm.educational_crm_backend.entity.enums.EnrollmentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {
    private UUID id;
    private UUID studentId;
    private String studentName;
    private UUID courseId;
    private String courseName;
    private LocalDateTime enrollmentDate;
    private EnrollmentStatus status;

}
