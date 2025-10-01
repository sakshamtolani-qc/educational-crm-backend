package com.crm.educational_crm_backend.service.course;

import com.crm.educational_crm_backend.dto.course.CourseRequest;
import com.crm.educational_crm_backend.dto.course.CourseResponse;

import java.util.List;
import java.util.UUID;

public interface CourseService {
    CourseResponse createCourse(CourseRequest request);
    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(UUID id);
    CourseResponse updateCourse(UUID id, CourseRequest request);
    void deleteCourse(UUID id);
}
