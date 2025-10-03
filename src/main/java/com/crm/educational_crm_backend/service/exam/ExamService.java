package com.crm.educational_crm_backend.service.exam;

import com.crm.educational_crm_backend.dto.exam.ExamRequest;
import com.crm.educational_crm_backend.dto.exam.ExamResponse;

import java.util.List;
import java.util.UUID;

public interface ExamService {
    ExamResponse createExam(ExamRequest dto);
    List<ExamResponse> getAllExams();
    ExamResponse getExamById(UUID id);
    void cancelExam(UUID id);
}
