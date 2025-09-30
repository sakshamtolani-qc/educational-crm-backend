package com.crm.educational_crm_backend.controller.faculty;

import com.crm.educational_crm_backend.dto.faculty.FacultyRequest;
import com.crm.educational_crm_backend.dto.faculty.FacultyResponse;
import com.crm.educational_crm_backend.service.faculty.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // Create a new faculty
    @PostMapping
    public ResponseEntity<FacultyResponse> createFaculty(@RequestBody FacultyRequest request) {
        FacultyResponse response = facultyService.createFaculty(request);
        return ResponseEntity.ok(response);
    }

    // Get all faculty
    @GetMapping
    public ResponseEntity<List<FacultyResponse>> getAllFaculty() {
        List<FacultyResponse> facultyList = facultyService.getAllFaculty();
        return ResponseEntity.ok(facultyList);
    }

    // Get faculty by ID
    @GetMapping("/{id}")
    public ResponseEntity<FacultyResponse> getFacultyById(@PathVariable UUID id) {
        FacultyResponse response = facultyService.getFacultyById(id);
        return ResponseEntity.ok(response);
    }

    // Update faculty
    @PutMapping("/{id}")
    public ResponseEntity<FacultyResponse> updateFaculty(@PathVariable UUID id,
                                                         @RequestBody FacultyRequest request) {
        FacultyResponse response = facultyService.updateFaculty(id, request);
        return ResponseEntity.ok(response);
    }

    // Delete faculty
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable UUID id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok("Faculty deleted successfully");
    }
}
