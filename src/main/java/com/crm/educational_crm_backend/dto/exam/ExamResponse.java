package com.crm.educational_crm_backend.dto.exam;

import com.crm.educational_crm_backend.entity.enums.ExamStatus;
import com.crm.educational_crm_backend.entity.enums.ExamType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class ExamResponse {
    private UUID id;
    private UUID courseId;
    private UUID subjectId;
    private LocalDateTime examDate;
    private ExamType type;
    private Double maxMarks;
    private ExamStatus status;
}
