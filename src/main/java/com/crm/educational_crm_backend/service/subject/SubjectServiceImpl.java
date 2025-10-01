package com.crm.educational_crm_backend.service.subject;

import com.crm.educational_crm_backend.dto.subject.SubjectRequest;
import com.crm.educational_crm_backend.dto.subject.SubjectResponse;
import com.crm.educational_crm_backend.entity.course.Course;
import com.crm.educational_crm_backend.entity.faculty.Faculty;
import com.crm.educational_crm_backend.entity.subject.Subject;
import com.crm.educational_crm_backend.exception.course.CourseNotFoundException;
import com.crm.educational_crm_backend.exception.faculty.FacultyNotFoundException;
import com.crm.educational_crm_backend.repository.course.CourseRepository;
import com.crm.educational_crm_backend.repository.faculty.FacultyRepository;
import com.crm.educational_crm_backend.repository.subject.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository,
                              CourseRepository courseRepository,
                              FacultyRepository facultyRepository) {
        this.subjectRepository = subjectRepository;
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }

    @Override
    public SubjectResponse createSubject(SubjectRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new FacultyNotFoundException("Faculty not found"));

        Subject subject = new Subject();
        subject.setCourse(course);
        subject.setFaculty(faculty);
        subject.setName(request.getName());
        subject.setCode(request.getCode());
        subject.setCredits(request.getCredits());

        Subject saved = subjectRepository.save(subject);
        return toResponse(saved);
    }

    @Override
    public List<SubjectResponse> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private SubjectResponse toResponse(Subject subject) {
        return new SubjectResponse(
                subject.getId(),
                subject.getCourse().getId(),
                subject.getFaculty().getId(),
                subject.getName(),
                subject.getCode(),
                subject.getCredits(),
                subject.getCreatedAt(),
                subject.getUpdatedAt()
        );
    }
}
