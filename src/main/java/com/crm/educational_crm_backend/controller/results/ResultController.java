package com.crm.educational_crm_backend.controller.results;

import com.crm.educational_crm_backend.dto.results.ResultRequest;
import com.crm.educational_crm_backend.entity.results.Result;
import com.crm.educational_crm_backend.service.results.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ResultController {

    private final ResultService resultService;

    // Add/submit results (Faculty / Admin)
    @PostMapping("/exams/{examId}/results")
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    public ResponseEntity<Result> addResult(
            @PathVariable UUID examId,
            @RequestBody ResultRequest request
    ) {
        return ResponseEntity.ok(resultService.addResult(examId, request));
    }

    // Re-evaluation (update marks/remarks) (Faculty / Admin)
    @PostMapping("/exams/{examId}/results/revaluation")
        @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
        public ResponseEntity<Result> revaluateResult(
                @PathVariable UUID examId,
                @RequestBody ResultRequest request
        ) 
        {
            
            Result result = resultService.getResultsByExam(examId).stream()
                    .filter(r -> r.getStudentId().equals(request.getStudentId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Result not found for studentId: " + request.getStudentId()));

            return ResponseEntity.ok(resultService.updateResult(result.getId(), request));
        }


    // Get exam results (Authenticated)
    @GetMapping("/exams/{examId}/results")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Result>> getResultsByExam(@PathVariable UUID examId) {
        return ResponseEntity.ok(resultService.getResultsByExam(examId));
    }

    // Get student results (Admin / Faculty / Student)
    @GetMapping("/students/{studentId}/results")
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY','STUDENT')")
    public ResponseEntity<List<Result>> getResultsByStudent(@PathVariable UUID studentId) {
        return ResponseEntity.ok(resultService.getResultsByStudent(studentId));
    }
}
