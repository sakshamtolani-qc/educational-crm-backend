package com.crm.educational_crm_backend.dto.admissions;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissionRequest {
    private UUID leadId;
    private UUID userId;
    private UUID courseId;
    private String remarks;
    private String documents; // JSON as string
}
