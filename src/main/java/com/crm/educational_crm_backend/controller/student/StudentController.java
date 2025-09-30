package com.crm.educational_crm_backend.controller.student;

import com.crm.educational_crm_backend.dto.student.StudentRequest;
import com.crm.educational_crm_backend.dto.student.StudentResponse;
import com.crm.educational_crm_backend.entity.student.Student;
import com.crm.educational_crm_backend.service.student.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Create student profile (Admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
        Student student = studentService.createStudent(request);
        return ResponseEntity.ok(studentService.mapToStudentResponse(student));
    }

    // Get all students (Authenticated)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> students = studentService.getAllStudentsResponse();
        return ResponseEntity.ok(students);
    }

    // Get student by ID (Authenticated)
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable UUID id) {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return ResponseEntity.ok(studentService.mapToStudentResponse(student));
    }

    // Update student profile (Admin / Self)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable UUID id, @RequestBody StudentRequest request) {
        Student updated = studentService.updateStudent(id, request);
        return ResponseEntity.ok(studentService.mapToStudentResponse(updated));
    }

    // Delete student (Admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
