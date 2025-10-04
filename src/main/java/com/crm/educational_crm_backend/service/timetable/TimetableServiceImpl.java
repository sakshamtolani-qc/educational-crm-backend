package com.crm.educational_crm_backend.service.timetable;

import com.crm.educational_crm_backend.dto.timetable.TimetableRequest;
import com.crm.educational_crm_backend.dto.timetable.TimetableResponse;
import com.crm.educational_crm_backend.entity.timetable.Timetable;
import com.crm.educational_crm_backend.entity.course.Course;
import com.crm.educational_crm_backend.entity.faculty.Faculty;
import com.crm.educational_crm_backend.entity.subject.Subject;
import com.crm.educational_crm_backend.exception.timetable.TimetableNotFoundException;
import com.crm.educational_crm_backend.repository.course.CourseRepository;
import com.crm.educational_crm_backend.repository.faculty.FacultyRepository;
import com.crm.educational_crm_backend.repository.subject.SubjectRepository;
import com.crm.educational_crm_backend.repository.timetable.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final TimetableRepository timetableRepository;
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final FacultyRepository facultyRepository;

    @Override
    public TimetableResponse createOrUpdateTimetable(TimetableRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new TimetableNotFoundException("Course not found"));

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() -> new TimetableNotFoundException("Subject not found"));

        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new TimetableNotFoundException("Faculty not found"));

        Timetable timetable = Timetable.builder()
                .course(course)
                .subject(subject)
                .faculty(faculty)
                .dayOfWeek(request.getDayOfWeek())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .room(request.getRoom())
                .build();

        timetable = timetableRepository.save(timetable);
        return mapToResponse(timetable);
    }

    @Override
    public List<TimetableResponse> getTimetableByCourse(UUID courseId) {
        return timetableRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TimetableResponse> getTimetableByFaculty(UUID facultyId) {
        return timetableRepository.findByFacultyId(facultyId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private TimetableResponse mapToResponse(Timetable t) {
        return TimetableResponse.builder()
                .id(t.getId())
                .courseId(t.getCourse().getId())
                .subjectId(t.getSubject().getId())
                .facultyId(t.getFaculty().getId())
                .dayOfWeek(t.getDayOfWeek())
                .startTime(t.getStartTime())
                .endTime(t.getEndTime())
                .room(t.getRoom())
                .createdAt(t.getCreatedAt())
                .updatedAt(t.getUpdatedAt())
                .build();
    }
}
