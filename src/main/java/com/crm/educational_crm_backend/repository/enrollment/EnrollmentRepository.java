package com.crm.educational_crm_backend.repository.enrollment;

import com.crm.educational_crm_backend.entity.enrollment.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface EnrollmentRepository extends JpaRepository<Enrollment, UUID> {
    List<Enrollment> findByStudentId(UUID studentId);
    List<Enrollment> findByCourseId(UUID courseId);
}
