package com.crm.educational_crm_backend.repository.student;

import com.crm.educational_crm_backend.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    // Find student by enrollment number
    Optional<Student> findByEnrollmentNo(String enrollmentNo);

    // Optional: find by User ID
    Optional<Student> findByUserId(UUID userId);

    // Optional: check if enrollment number exists
    boolean existsByEnrollmentNo(String enrollmentNo);

     // Count students by admission month
    @Query("SELECT FUNCTION('MONTHNAME', s.admissionDate), COUNT(s) FROM Student s GROUP BY FUNCTION('MONTHNAME', s.admissionDate)")
    List<Object[]> countByMonth();

    @Query("SELECT e.course.id, COUNT(e.student.id) FROM Enrollment e GROUP BY e.course.id")
    List<Object[]> countStudentsByCourse();


}
