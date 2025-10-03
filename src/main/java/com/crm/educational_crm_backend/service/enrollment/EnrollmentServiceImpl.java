package com.crm.educational_crm_backend.service.enrollment;

import com.crm.educational_crm_backend.dto.enrollment.EnrollmentRequest;
import com.crm.educational_crm_backend.dto.enrollment.EnrollmentResponse;
import com.crm.educational_crm_backend.entity.enrollment.Enrollment;
import com.crm.educational_crm_backend.entity.student.Student;
import com.crm.educational_crm_backend.entity.course.Course;
import com.crm.educational_crm_backend.exception.course.CourseNotFoundException;
import com.crm.educational_crm_backend.exception.student.StudentNotFoundException;
import com.crm.educational_crm_backend.repository.enrollment.EnrollmentRepository;
import com.crm.educational_crm_backend.repository.student.StudentRepository;
import com.crm.educational_crm_backend.repository.course.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public EnrollmentResponse enrollStudent(EnrollmentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new StudentNotFoundException(
                        "Student not found with ID: " + request.getStudentId()
                ));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException(
                        "Course not found with ID: " + request.getCourseId()
                ));

        // Builder pattern for Enrollment
        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .enrollmentDate(request.getEnrollmentDate())
                .status(request.getStatus())
                .build();

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
        // Builder pattern for EnrollmentResponse
        return EnrollmentResponse.builder()
                .id(enrollment.getId())
                .studentId(enrollment.getStudent().getId())
                .studentName(enrollment.getStudent().getUser().getUsername())
                .courseId(enrollment.getCourse().getId())
                .courseName(enrollment.getCourse().getName())
                .enrollmentDate(enrollment.getEnrollmentDate())
                .status(enrollment.getStatus())
                .build();
    }
}
