package com.crm.educational_crm_backend.controller.reports;

import com.crm.educational_crm_backend.dto.reports.*;
import com.crm.educational_crm_backend.service.reports.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/admissions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdmissionReportResponse> getAdmissionsReport() {
        return ResponseEntity.ok(reportService.getAdmissionsReport());
    }

    @GetMapping("/attendance")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AttendanceReportResponse> getAttendanceReport() {
        return ResponseEntity.ok(reportService.getAttendanceReport());
    }

    @GetMapping("/exams")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ExamReportResponse> getExamReport() {
        return ResponseEntity.ok(reportService.getExamReport());
    }

    @GetMapping("/fees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FeeReportResponse> getFeeReport() {
        return ResponseEntity.ok(reportService.getFeeReport());
    }

    @GetMapping("/custom")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomReportResponse> getCustomReport(
            @RequestParam String reportType,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    ) {
        return ResponseEntity.ok(reportService.getCustomReport(reportType, fromDate, toDate));
    }
}
