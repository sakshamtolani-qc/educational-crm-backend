package com.crm.educational_crm_backend.dto.admissions;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadRequest {
    private String name;
    private String email;
    private String phone;
    private UUID interestedCourseId;
    private String notes;
}
