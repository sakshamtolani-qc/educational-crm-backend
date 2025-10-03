package com.crm.educational_crm_backend.service.attendance;

import com.crm.educational_crm_backend.dto.attendance.AttendanceRequest;
import com.crm.educational_crm_backend.dto.attendance.AttendanceResponse;

import java.util.List;
import java.util.UUID;

public interface AttendanceService {
    AttendanceResponse markAttendance(AttendanceRequest request);
    List<AttendanceResponse> getAttendanceByStudent(UUID studentId);
}
