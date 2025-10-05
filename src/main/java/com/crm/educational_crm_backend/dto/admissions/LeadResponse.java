package com.crm.educational_crm_backend.dto.admissions;

import com.crm.educational_crm_backend.entity.enums.LeadStatus;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadResponse {
    private UUID id;
    private String name;
    private String email;
    private String phone;
    private UUID interestedCourseId;
    private LeadStatus status;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
