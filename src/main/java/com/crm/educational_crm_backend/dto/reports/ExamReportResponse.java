package com.crm.educational_crm_backend.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class ExamReportResponse {
    private double avgScore;
    private double passPercentage;
    private Map<String, Double> byCourse;
    private Map<String, Double> bySubject;
}
