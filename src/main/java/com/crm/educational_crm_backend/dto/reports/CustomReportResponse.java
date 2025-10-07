package com.crm.educational_crm_backend.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
@AllArgsConstructor
public class CustomReportResponse {
    private String reportType;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Map<String, Object> data;
}
