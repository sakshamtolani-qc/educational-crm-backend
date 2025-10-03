package com.crm.educational_crm_backend.dto.subject;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectResponse {
    private UUID id;
    private UUID courseId;
    private UUID facultyId;
    private String name;
    private String code;
    private int credits;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
