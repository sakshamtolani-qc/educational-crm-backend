package com.crm.educational_crm_backend.service.enrollment;

import com.crm.educational_crm_backend.dto.enrollment.EnrollmentRequest;
import com.crm.educational_crm_backend.dto.enrollment.EnrollmentResponse;
import com.crm.educational_crm_backend.entity.enrollment.Enrollment;
import com.crm.educational_crm_backend.entity.student.Student;
import com.crm.educational_crm_backend.exception.course.CourseNotFoundException;
import com.crm.educational_crm_backend.exception.student.StudentNotFoundException;
import com.crm.educational_crm_backend.entity.course.Course;
import com.crm.educational_crm_backend.repository.enrollment.EnrollmentRepository;
import com.crm.educational_crm_backend.repository.student.StudentRepository;
import com.crm.educational_crm_backend.repository.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public EnrollmentResponse enrollStudent(EnrollmentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
            .orElseThrow(() -> new StudentNotFoundException(
                "Student not found with ID: " + request.getStudentId().toString()
            ));

        Course course = courseRepository.findById(request.getCourseId())
            .orElseThrow(() -> new CourseNotFoundException(
                "Course not found with ID: " + request.getCourseId().toString()
            ));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        if (request.getEnrollmentDate() != null) enrollment.setEnrollmentDate(request.getEnrollmentDate());
        if (request.getStatus() != null) enrollment.setStatus(request.getStatus());

        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToResponse(saved);
    }


    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private EnrollmentResponse mapToResponse(Enrollment enrollment) {
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId(enrollment.getId());
        response.setStudentId(enrollment.getStudent().getId());
        response.setStudentName(enrollment.getStudent().getUser().getUsername());
        response.setCourseId(enrollment.getCourse().getId());
        response.setCourseName(enrollment.getCourse().getName());
        response.setEnrollmentDate(enrollment.getEnrollmentDate());
        response.setStatus(enrollment.getStatus());
        return response;
    }
}
