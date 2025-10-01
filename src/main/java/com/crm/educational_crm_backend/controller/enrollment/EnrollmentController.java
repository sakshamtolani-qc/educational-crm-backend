package com.crm.educational_crm_backend.controller.enrollment;

import com.crm.educational_crm_backend.dto.enrollment.EnrollmentRequest;
import com.crm.educational_crm_backend.dto.enrollment.EnrollmentResponse;
import com.crm.educational_crm_backend.service.enrollment.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Enroll student in course (Admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EnrollmentResponse> enrollStudent(@RequestBody EnrollmentRequest request) {
        EnrollmentResponse response = enrollmentService.enrollStudent(request);
        return ResponseEntity.ok(response);
    }

    // List all enrollments (Admin / Faculty)
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments() {
        List<EnrollmentResponse> enrollments = enrollmentService.getAllEnrollments();
        return ResponseEntity.ok(enrollments);
    }
}
