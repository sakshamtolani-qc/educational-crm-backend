package com.crm.educational_crm_backend.service.attendance;

import com.crm.educational_crm_backend.dto.attendance.AttendanceRequest;
import com.crm.educational_crm_backend.dto.attendance.AttendanceResponse;
import com.crm.educational_crm_backend.entity.attendance.Attendance;
import com.crm.educational_crm_backend.repository.attendance.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public AttendanceResponse markAttendance(AttendanceRequest request) {
        Attendance attendance = Attendance.builder()
        .studentId(request.getStudentId())
        .subjectId(request.getSubjectId())
        .attendanceDate(request.getAttendanceDate())
        .status(request.getStatus())
        .build();
        Attendance saved = attendanceRepository.save(attendance);
        return new AttendanceResponse(
                saved.getId(),
                saved.getStudentId(),
                saved.getSubjectId(),
                saved.getAttendanceDate(),
                saved.getStatus()
        );
    }

    @Override
    public List<AttendanceResponse> getAttendanceByStudent(UUID studentId) {
        return attendanceRepository.findByStudentId(studentId)
                .stream()
                .map(a -> new AttendanceResponse(
                        a.getId(),
                        a.getStudentId(),
                        a.getSubjectId(),
                        a.getAttendanceDate(),
                        a.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
