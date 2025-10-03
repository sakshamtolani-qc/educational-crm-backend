package com.crm.educational_crm_backend.service.course_faculty;

import com.crm.educational_crm_backend.dto.course_faculty.AssignFacultyRequest;
import com.crm.educational_crm_backend.dto.course_faculty.CourseFacultyResponse;
import com.crm.educational_crm_backend.entity.course.Course;
import com.crm.educational_crm_backend.entity.faculty.Faculty;
import com.crm.educational_crm_backend.entity.student.Student;
import com.crm.educational_crm_backend.entity.course_faculty.CourseFaculty;
import com.crm.educational_crm_backend.exception.course_faculty.CourseNotFoundException;
import com.crm.educational_crm_backend.exception.course_faculty.FacultyNotFoundException;
import com.crm.educational_crm_backend.repository.course.CourseRepository;
import com.crm.educational_crm_backend.repository.enrollment.EnrollmentRepository;
import com.crm.educational_crm_backend.repository.faculty.FacultyRepository;
import com.crm.educational_crm_backend.repository.course_faculty.CourseFacultyRepository;
import lombok.RequiredArgsConstructor;
import com.crm.educational_crm_backend.entity.enums.FacultyRole;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseFacultyServiceImpl implements CourseFacultyService {

    private final CourseFacultyRepository courseFacultyRepository;
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Override
    public CourseFacultyResponse assignFaculty(UUID courseId, AssignFacultyRequest request) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found"));

            CourseFaculty courseFaculty = CourseFaculty.builder()
            .course(course)
            .faculty(faculty)
            .role(request.getRole() != null ? request.getRole() : FacultyRole.COORDINATOR)
            .build();


        courseFaculty = courseFacultyRepository.save(courseFaculty);
        return mapToResponse(courseFaculty);
    }

    @Override
    public List<CourseFacultyResponse> getFacultyByCourse(UUID courseId) {
        return courseFacultyRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getStudentsByCourse(UUID courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(enrollment -> enrollment.getStudent())
                .collect(Collectors.toList());
    }

    private CourseFacultyResponse mapToResponse(CourseFaculty cf) {
        return CourseFacultyResponse.builder()
                .id(cf.getId())
                .courseId(cf.getCourse().getId())
                .facultyId(cf.getFaculty().getId())
                .role(cf.getRole())
                .assignedAt(cf.getAssignedAt())
                .build();
    }
}
