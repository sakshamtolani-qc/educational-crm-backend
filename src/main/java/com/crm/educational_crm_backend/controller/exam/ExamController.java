package com.crm.educational_crm_backend.controller.exam;

import com.crm.educational_crm_backend.dto.exam.ExamRequest;
import com.crm.educational_crm_backend.dto.exam.ExamResponse;
import com.crm.educational_crm_backend.service.exam.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    // Schedule new exam (Admin / Faculty)
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    public ResponseEntity<ExamResponse> createExam(@RequestBody ExamRequest request) {
        return ResponseEntity.ok(examService.createExam(request));
    }

    // List all exams (Authenticated)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ExamResponse>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }


    // Get exam details (Authenticated)
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ExamResponse> getExamById(@PathVariable UUID id) {
        return ResponseEntity.ok(examService.getExamById(id));
    }

    // Cancel exam (Admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> cancelExam(@PathVariable UUID id) {
        examService.cancelExam(id);
        return ResponseEntity.noContent().build();
    }
}
