package com.crm.educational_crm_backend.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class AdmissionReportResponse {
    private int totalAdmissions;
    private Map<String, Long> byCourse;
    private Map<String, Long> byMonth;
}
