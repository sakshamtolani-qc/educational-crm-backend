package com.crm.educational_crm_backend.repository.attendance;

import com.crm.educational_crm_backend.entity.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface AttendanceRepository extends JpaRepository<Attendance, UUID> {
    List<Attendance> findByStudentId(UUID studentId);

     // For breakdown by subject or course
    @Query("SELECT a.subjectId, COUNT(a) FROM Attendance a GROUP BY a.subjectId")
    List<Object[]> countBySubject();

    @Query("SELECT a.studentId, COUNT(a) FROM Attendance a GROUP BY a.studentId")
    List<Object[]> countByStudent();

    List<Attendance> findByAttendanceDateBetween(LocalDate start, LocalDate end);

    @Query(value = """
    SELECT e.course_id, COUNT(a.id)
    FROM attendance a
    JOIN enrollments e ON a.student_id = e.student_id
    WHERE a.attendance_date BETWEEN :start AND :end
    GROUP BY e.course_id
    """, nativeQuery = true)
    List<Object[]> countAttendanceByCourse(LocalDate start, LocalDate end);

}   
