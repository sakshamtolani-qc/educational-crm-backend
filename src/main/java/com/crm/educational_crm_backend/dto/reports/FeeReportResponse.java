package com.crm.educational_crm_backend.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class FeeReportResponse {
    private double totalCollected;
    private double totalPending;
    private Map<String, Double> byCourse;   // Course names
}
