package com.crm.educational_crm_backend.service.faculty;

import com.crm.educational_crm_backend.dto.faculty.FacultyRequest;
import com.crm.educational_crm_backend.dto.faculty.FacultyResponse;
import com.crm.educational_crm_backend.entity.faculty.Faculty;
import com.crm.educational_crm_backend.entity.user.User;
import com.crm.educational_crm_backend.repository.faculty.FacultyRepository;
import com.crm.educational_crm_backend.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private UserRepository userRepository;

    // Convert Faculty entity to FacultyResponse DTO
    private FacultyResponse mapToResponse(Faculty faculty) {
        return new FacultyResponse(
                faculty.getId(),
                faculty.getUser().getId(),
                faculty.getUser().getUsername(),
                faculty.getDepartment(),
                faculty.getSpecialization(),
                faculty.getHireDate(),
                faculty.getDesignation(),
                faculty.getCreatedAt(),
                faculty.getUpdatedAt()
        );
    }

    @Override
    public FacultyResponse createFaculty(FacultyRequest request) {
        // Check if user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Optional: check if faculty already exists for this user
        if(facultyRepository.existsByUserId(user.getId())) {
            throw new RuntimeException("Faculty already exists for this user");
        }

        Faculty faculty = new Faculty();
        faculty.setUser(user);
        faculty.setDepartment(request.getDepartment());
        faculty.setSpecialization(request.getSpecialization());
        faculty.setHireDate(request.getHireDate());
        faculty.setDesignation(request.getDesignation());
        faculty.setCreatedAt(LocalDateTime.now());
        faculty.setUpdatedAt(LocalDateTime.now());

        Faculty savedFaculty = facultyRepository.save(faculty);

        return mapToResponse(savedFaculty);
    }

    @Override
    public List<FacultyResponse> getAllFaculty() {
        return facultyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyResponse getFacultyById(UUID id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        return mapToResponse(faculty);
    }

    @Override
    public FacultyResponse updateFaculty(UUID id, FacultyRequest request) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));

        faculty.setDepartment(request.getDepartment());
        faculty.setSpecialization(request.getSpecialization());
        faculty.setHireDate(request.getHireDate());
        faculty.setDesignation(request.getDesignation());
        faculty.setUpdatedAt(LocalDateTime.now());

        Faculty updatedFaculty = facultyRepository.save(faculty);
        return mapToResponse(updatedFaculty);
    }

    @Override
    public void deleteFaculty(UUID id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        facultyRepository.delete(faculty);
    }
}
