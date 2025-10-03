package com.crm.educational_crm_backend.dto.subject;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectRequest {
    private UUID courseId;
    private UUID facultyId;
    private String name;
    private String code;
    private int credits;
}