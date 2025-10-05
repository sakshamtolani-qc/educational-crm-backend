package com.crm.educational_crm_backend.controller.admissions;

import com.crm.educational_crm_backend.dto.admissions.AdmissionRequest;
import com.crm.educational_crm_backend.dto.admissions.AdmissionResponse;
import com.crm.educational_crm_backend.service.admissions.AdmissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    private final AdmissionService admissionService;

    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','PUBLIC')")
    @PostMapping
    public ResponseEntity<AdmissionResponse> createAdmission(@RequestBody AdmissionRequest request) {
        AdmissionResponse response = admissionService.createAdmission(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<AdmissionResponse> getAdmissionById(@PathVariable UUID id) {
        AdmissionResponse response = admissionService.getAdmissionById(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<AdmissionResponse>> getAllAdmissions() {
        List<AdmissionResponse> responses = admissionService.getAllAdmissions();
        return ResponseEntity.ok(responses);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/approve")
    public ResponseEntity<AdmissionResponse> approveAdmission(@PathVariable UUID id) {
        AdmissionResponse response = admissionService.approveAdmission(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/reject")
    public ResponseEntity<AdmissionResponse> rejectAdmission(@PathVariable UUID id) {
        AdmissionResponse response = admissionService.rejectAdmission(id);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AdmissionResponse> updateAdmission(@PathVariable UUID id,
                                                             @RequestBody AdmissionRequest request) {
        AdmissionResponse response = admissionService.updateAdmission(id, request);
        return ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmission(@PathVariable UUID id) {
        admissionService.deleteAdmission(id);
        return ResponseEntity.noContent().build();
    }
}
