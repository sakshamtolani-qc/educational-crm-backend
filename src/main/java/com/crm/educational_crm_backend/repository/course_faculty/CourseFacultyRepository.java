package com.crm.educational_crm_backend.repository.course_faculty;

import com.crm.educational_crm_backend.entity.course_faculty.CourseFaculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseFacultyRepository extends JpaRepository<CourseFaculty, UUID> {
    List<CourseFaculty> findByCourseId(UUID courseId);
}
