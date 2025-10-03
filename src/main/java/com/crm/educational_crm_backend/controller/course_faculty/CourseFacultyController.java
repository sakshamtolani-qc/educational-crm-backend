package com.crm.educational_crm_backend.controller.course_faculty;

import com.crm.educational_crm_backend.dto.course_faculty.AssignFacultyRequest;
import com.crm.educational_crm_backend.dto.course_faculty.CourseFacultyResponse;
import com.crm.educational_crm_backend.entity.student.Student;
import com.crm.educational_crm_backend.service.course_faculty.CourseFacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/courses/{courseId}/faculty")
@RequiredArgsConstructor
public class CourseFacultyController {

    private final CourseFacultyService courseFacultyService;

    // Assign faculty -> Admin only
    @PostMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CourseFacultyResponse> assignFaculty(
            @PathVariable UUID courseId,
            @RequestBody AssignFacultyRequest request) {
        return ResponseEntity.ok(courseFacultyService.assignFaculty(courseId, request));
    }

    // List all faculty -> Admin or Faculty
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    public ResponseEntity<List<CourseFacultyResponse>> getFacultyByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(courseFacultyService.getFacultyByCourse(courseId));
    }

    // List all students -> Admin or Faculty
    @GetMapping("/students")
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    public ResponseEntity<List<Student>> getStudentsByCourse(@PathVariable UUID courseId) {
        List<Student> students = courseFacultyService.getStudentsByCourse(courseId);
        return ResponseEntity.ok(students);
    }
}
