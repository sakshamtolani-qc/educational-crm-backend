package com.crm.educational_crm_backend.service.results;

import com.crm.educational_crm_backend.dto.results.ResultRequest;
import com.crm.educational_crm_backend.entity.results.Result;
import com.crm.educational_crm_backend.exception.exam.ExamNotFoundException;
import com.crm.educational_crm_backend.exception.results.ResultNotFoundException;
import com.crm.educational_crm_backend.repository.exam.ExamRepository;
import com.crm.educational_crm_backend.repository.results.ResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ExamRepository examRepository;

    @Override
    public Result addResult(UUID examId, ResultRequest dto) {
        examRepository.findById(examId)
                .orElseThrow(() -> new ExamNotFoundException("Exam not found with id: " + examId));

        if (dto.getMarksObtained() == null) {
            throw new IllegalArgumentException("Marks must be provided when creating a result");
        }

        String grade = calculateGrade(dto.getMarksObtained());

        Result result = Result.builder()
                .examId(examId)
                .studentId(dto.getStudentId())
                .marksObtained(dto.getMarksObtained())
                .remarks(null)
                .grade(grade)
                .build();

        return resultRepository.save(result);
    }

    @Override
    public Result updateResult(UUID resultId, ResultRequest dto) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new ResultNotFoundException("Result not found with id: " + resultId));

        // Update remarks only
        if (dto.getRemarks() != null) {
            result.setRemarks(dto.getRemarks());
        }

        // Recalculate grade based on existing marks
        if (result.getMarksObtained() != null) {
            result.setGrade(calculateGrade(result.getMarksObtained()));
        }

        return resultRepository.save(result);
    }




    @Override
    public List<Result> getResultsByExam(UUID examId) {
        return resultRepository.findByExamId(examId);
    }

    @Override
    public List<Result> getResultsByStudent(UUID studentId) {
        return resultRepository.findByStudentId(studentId);
    }

    private String calculateGrade(Double marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B+";
        if (marks >= 60) return "B";
        if (marks >= 50) return "C+";
        if (marks >= 40) return "C";
        return "F";
    }
}
