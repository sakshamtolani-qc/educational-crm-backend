package com.crm.educational_crm_backend.service.course;

import com.crm.educational_crm_backend.dto.course.CourseRequest;
import com.crm.educational_crm_backend.dto.course.CourseResponse;
import com.crm.educational_crm_backend.entity.course.Course;
import com.crm.educational_crm_backend.exception.course.CourseNotFoundException;
import com.crm.educational_crm_backend.repository.course.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponse createCourse(CourseRequest request) {
    Course course = Course.builder()
            .name(request.getName())
            .code(request.getCode())
            .description(request.getDescription())
            .credits(request.getCredits())
            .durationMonths(request.getDurationMonths())
            .build();

    Course saved = courseRepository.save(course);
    return toResponse(saved);
    }


    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponse getCourseById(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + id));
        return toResponse(course);
    }

    @Override
    public CourseResponse updateCourse(UUID id, CourseRequest request) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + id));

        course.setName(request.getName());
        course.setCode(request.getCode());
        course.setDescription(request.getDescription());
        course.setCredits(request.getCredits());
        course.setDurationMonths(request.getDurationMonths());
        Course updated = courseRepository.save(course);

        return toResponse(updated);
    }

    @Override
    public void deleteCourse(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id " + id));
        courseRepository.delete(course);
    }

    private CourseResponse toResponse(Course course) {
        return new CourseResponse(
                course.getId().toString(),
                course.getName(),
                course.getCode(),
                course.getDescription(),
                course.getCredits(),
                course.getDurationMonths(),
                course.getCreatedAt(),
                course.getUpdatedAt()
        );
    }
}
