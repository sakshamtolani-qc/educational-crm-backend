package com.crm.educational_crm_backend.repository.fee;

import com.crm.educational_crm_backend.entity.fee.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface FeeRepository extends JpaRepository<Fee, UUID> {

    List<Fee> findByStudentId(UUID studentId);

    @Query("SELECT f.courseId, SUM(f.amount) FROM Fee f WHERE f.status = :status GROUP BY f.courseId")
    List<Object[]> sumByCourseId(String status);

    @Query("SELECT f.studentId, SUM(f.amount) FROM Fee f WHERE f.status = :status GROUP BY f.studentId")
    List<Object[]> sumByStudentId(String status);

    List<Fee> findByPaidDateBetween(LocalDate start, LocalDate end);

    @Query("""
    SELECT f.courseId, SUM(f.amount)
    FROM Fee f
    WHERE f.status = 'PAID' AND f.paidDate BETWEEN :start AND :end
    GROUP BY f.courseId
    """)
    List<Object[]> sumPaidByCourse(LocalDate start, LocalDate end);

    @Query("""
        SELECT f.courseId, SUM(f.amount)
        FROM Fee f
        WHERE f.status = 'PENDING' AND f.paidDate BETWEEN :start AND :end
        GROUP BY f.courseId
    """)
    List<Object[]> sumPendingByCourse(LocalDate start, LocalDate end);

}

