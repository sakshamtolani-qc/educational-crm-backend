package com.crm.educational_crm_backend.repository.student;

import com.crm.educational_crm_backend.entity.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
