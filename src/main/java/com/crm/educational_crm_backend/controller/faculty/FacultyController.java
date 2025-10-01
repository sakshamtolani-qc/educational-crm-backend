package com.crm.educational_crm_backend.controller.faculty;

import com.crm.educational_crm_backend.dto.faculty.FacultyRequest;
import com.crm.educational_crm_backend.dto.faculty.FacultyResponse;
import com.crm.educational_crm_backend.service.faculty.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // Create faculty profile (Admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacultyResponse> createFaculty(@RequestBody FacultyRequest request) {
        FacultyResponse response = facultyService.createFaculty(request);
        return ResponseEntity.ok(response);
    }

    // List all faculty (Authenticated users)
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<FacultyResponse>> getAllFaculty() {
        List<FacultyResponse> facultyList = facultyService.getAllFaculty();
        return ResponseEntity.ok(facultyList);
    }

    // Get faculty profile by ID (Authenticated users)
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<FacultyResponse> getFacultyById(@PathVariable UUID id) {
        FacultyResponse response = facultyService.getFacultyById(id);
        return ResponseEntity.ok(response);
    }

    // Update faculty (Admin only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacultyResponse> updateFaculty(@PathVariable UUID id,
                                                         @RequestBody FacultyRequest request) {
        FacultyResponse response = facultyService.updateFaculty(id, request);
        return ResponseEntity.ok(response);
    }

    // Delete faculty (Admin only)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteFaculty(@PathVariable UUID id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok("Faculty deleted successfully");
    }
}
