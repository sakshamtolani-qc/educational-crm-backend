package com.crm.educational_crm_backend.dto.attendance;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRequest {
    private UUID studentId;
    private UUID subjectId;
    private LocalDate attendanceDate;
    private String status;

    
}
