package com.crm.educational_crm_backend.dto.faculty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    
}
