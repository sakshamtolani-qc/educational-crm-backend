package com.crm.educational_crm_backend.service.student;

import com.crm.educational_crm_backend.dto.student.StudentRequest;
import com.crm.educational_crm_backend.dto.student.StudentResponse;
import com.crm.educational_crm_backend.entity.student.Student;
import com.crm.educational_crm_backend.entity.user.User;
import com.crm.educational_crm_backend.exception.user.UserNotFoundException;
import com.crm.educational_crm_backend.repository.student.StudentRepository;
import com.crm.educational_crm_backend.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Student createStudent(StudentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + request.getUserId()));

        Student student = Student.builder()
                .user(user)
                .enrollmentNo(request.getEnrollmentNo())
                .dob(request.getDob())
                .address(request.getAddress())
                .admissionDate(request.getAdmissionDate())
                .enrollmentDate(request.getEnrollmentDate())
                .build();

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(UUID id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student updateStudent(UUID id, StudentRequest request) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        existing.setEnrollmentNo(request.getEnrollmentNo());
        existing.setDob(request.getDob());
        existing.setAddress(request.getAddress());
        existing.setAdmissionDate(request.getAdmissionDate());
        existing.setEnrollmentDate(request.getEnrollmentDate());

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + request.getUserId()));
            existing.setUser(user);
        }

        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(UUID id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponse mapToStudentResponse(Student student) {
        return new StudentResponse(
                student.getId(),
                student.getUser().getId(),
                student.getEnrollmentNo(),
                student.getDob(),
                student.getAddress(),
                student.getAdmissionDate(),
                student.getEnrollmentDate()
        );
    }

    @Override
    public List<StudentResponse> getAllStudentsResponse() {
        return getAllStudents().stream()
                .map(this::mapToStudentResponse)
                .collect(Collectors.toList());
    }
}
