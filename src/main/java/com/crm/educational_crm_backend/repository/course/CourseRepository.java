package com.crm.educational_crm_backend.repository.course;

import com.crm.educational_crm_backend.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    boolean existsByName(String name);
    boolean existsByCode(String code);
}
