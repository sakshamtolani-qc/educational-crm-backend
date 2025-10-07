package com.crm.educational_crm_backend.dto.reports;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class AttendanceReportResponse {
    private double avgAttendance;
    private Map<String, Double> byCourse;   // Course names
    private Map<String, Double> bySubject;  // Subject names
}
