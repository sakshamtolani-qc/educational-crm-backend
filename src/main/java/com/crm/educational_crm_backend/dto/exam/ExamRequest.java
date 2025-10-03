package com.crm.educational_crm_backend.dto.exam;

import com.crm.educational_crm_backend.entity.enums.ExamType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ExamRequest {
    private UUID courseId;
    private UUID subjectId;
    private LocalDateTime examDate;
    private ExamType type;
    private Double maxMarks;
}
