package com.crm.educational_crm_backend.service.exam;

import com.crm.educational_crm_backend.dto.exam.ExamRequest;
import com.crm.educational_crm_backend.dto.exam.ExamResponse;
import com.crm.educational_crm_backend.entity.exam.Exam;
import com.crm.educational_crm_backend.entity.enums.ExamStatus;
import com.crm.educational_crm_backend.exception.exam.ExamNotFoundException;
import com.crm.educational_crm_backend.repository.exam.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    @Override
    public ExamResponse createExam(ExamRequest dto) {
        Exam exam = Exam.builder()
                .courseId(dto.getCourseId())
                .subjectId(dto.getSubjectId())
                .examDate(dto.getExamDate())
                .type(dto.getType())
                .maxMarks(dto.getMaxMarks())
                .status(ExamStatus.SCHEDULED)
                .build();

        Exam saved = examRepository.save(exam);

        return ExamResponse.builder()
                .id(saved.getId())
                .courseId(saved.getCourseId())
                .subjectId(saved.getSubjectId())
                .examDate(saved.getExamDate())
                .type(saved.getType())
                .maxMarks(saved.getMaxMarks())
                .status(saved.getStatus())
                .build();
    }

    @Override
    public List<ExamResponse> getAllExams() {
        return examRepository.findAll().stream()
                .map(e -> ExamResponse.builder()
                        .id(e.getId())
                        .courseId(e.getCourseId())
                        .subjectId(e.getSubjectId())
                        .examDate(e.getExamDate())
                        .type(e.getType())
                        .maxMarks(e.getMaxMarks())
                        .status(e.getStatus())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ExamResponse getExamById(UUID id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: " + id));

        return ExamResponse.builder()
                .id(exam.getId())
                .courseId(exam.getCourseId())
                .subjectId(exam.getSubjectId())
                .examDate(exam.getExamDate())
                .type(exam.getType())
                .maxMarks(exam.getMaxMarks())
                .status(exam.getStatus())
                .build();
    }

    @Override
    public void cancelExam(UUID id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: " + id));
        exam.setStatus(ExamStatus.CANCELLED);
        examRepository.save(exam);
    }
}