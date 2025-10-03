package com.crm.educational_crm_backend.dto.faculty;

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
public class FacultyRequest {

    private UUID userId;
    private String department;
    private String specialization;
    private LocalDate hireDate;
    private String designation;

    
}
