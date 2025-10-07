package com.crm.educational_crm_backend.repository.results;

import com.crm.educational_crm_backend.entity.results.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ResultRepository extends JpaRepository<Result, UUID> {

    List<Result> findByExamId(UUID examId);
    List<Result> findByStudentId(UUID studentId);

    long countByMarksObtainedGreaterThanEqual(double marks);
    long countByMarksObtainedLessThan(double marks);

    @Query("SELECT AVG(r.marksObtained) FROM Result r WHERE r.examId = :examId")
    Double avgByExam(UUID examId);

    @Query("SELECT AVG(r.marksObtained) FROM Result r WHERE r.studentId = :studentId")
    Double avgByStudent(UUID studentId);

    @Query("SELECT r FROM Result r JOIN Exam e ON r.examId = e.id WHERE e.examDate BETWEEN :start AND :end")
    List<Result> findByExamDateBetween(LocalDate start, LocalDate end);

    @Query("""
    SELECT e.courseId, AVG(r.marksObtained)
    FROM Result r
    JOIN Exam e ON r.examId = e.id
    WHERE e.examDate BETWEEN :start AND :end
    GROUP BY e.courseId
    """)
    List<Object[]> avgMarksByCourse(LocalDate start, LocalDate end);

}
