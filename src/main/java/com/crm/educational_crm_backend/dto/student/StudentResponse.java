package com.crm.educational_crm_backend.dto.student;

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
public class StudentResponse {

    private UUID id;
    private UUID userId;
    private String enrollmentNo;
    private LocalDate dob;
    private String address;
    private LocalDate admissionDate;
    private LocalDate enrollmentDate;

}
