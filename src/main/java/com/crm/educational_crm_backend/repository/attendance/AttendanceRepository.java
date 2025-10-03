package com.crm.educational_crm_backend.repository.attendance;

import com.crm.educational_crm_backend.entity.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    List<Attendance> findByStudentId(UUID studentId);
}
