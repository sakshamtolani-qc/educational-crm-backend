package com.crm.educational_crm_backend.dto.admissions;

import com.crm.educational_crm_backend.entity.enums.AdmissionStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissionResponse {

    private UUID id;
    private UUID leadId;
    private UUID userId;
    private UUID courseId;
    private LocalDateTime applicationDate;
    private AdmissionStatus status;
    private String remarks;
    private String documents; // JSON as string
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
