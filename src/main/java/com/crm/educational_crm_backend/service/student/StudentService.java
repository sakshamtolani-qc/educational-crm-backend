package com.crm.educational_crm_backend.service.student;

import com.crm.educational_crm_backend.dto.student.StudentRequest;
import com.crm.educational_crm_backend.dto.student.StudentResponse;
import com.crm.educational_crm_backend.entity.student.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentService {

    // Create a new student (Admin only)
    Student createStudent(StudentRequest request);

    // Get all students (Authenticated)
    List<Student> getAllStudents();

    // Get student by ID (Authenticated)
    Optional<Student> getStudentById(UUID id);

    // Update student profile (Admin / Self)
    Student updateStudent(UUID id, StudentRequest request);

    // Delete student (Admin only)
    void deleteStudent(UUID id);

    // Map entity to DTO
    StudentResponse mapToStudentResponse(Student student);

    // Get all students as DTO list
    List<StudentResponse> getAllStudentsResponse();
}
