package com.crm.educational_crm_backend.service.reports;

import com.crm.educational_crm_backend.dto.reports.*;
import java.time.LocalDate;

public interface ReportService {
    AdmissionReportResponse getAdmissionsReport();
    AttendanceReportResponse getAttendanceReport();
    ExamReportResponse getExamReport();
    FeeReportResponse getFeeReport();
    CustomReportResponse getCustomReport(String reportType, LocalDate fromDate, LocalDate toDate);
}
