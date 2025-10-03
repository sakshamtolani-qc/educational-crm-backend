package com.crm.educational_crm_backend.service.course_faculty;

import com.crm.educational_crm_backend.dto.course_faculty.AssignFacultyRequest;
import com.crm.educational_crm_backend.dto.course_faculty.CourseFacultyResponse;
import com.crm.educational_crm_backend.entity.student.Student;

import java.util.List;
import java.util.UUID;

public interface CourseFacultyService {

    // Assign a faculty to a course
    CourseFacultyResponse assignFaculty(UUID courseId, AssignFacultyRequest request);

    // List all faculty assigned to a course
    List<CourseFacultyResponse> getFacultyByCourse(UUID courseId);

    // List all students enrolled in a course
    List<Student> getStudentsByCourse(UUID courseId);
}
