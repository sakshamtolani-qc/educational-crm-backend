package com.crm.educational_crm_backend.controller.admissions;

import com.crm.educational_crm_backend.dto.admissions.LeadRequest;
import com.crm.educational_crm_backend.dto.admissions.LeadResponse;
import com.crm.educational_crm_backend.service.admissions.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }

    // Public / Admin
    @PreAuthorize("hasAnyRole('ADMIN','PUBLIC')")
    @PostMapping
    public ResponseEntity<LeadResponse> createLead(@RequestBody LeadRequest request) {
        LeadResponse response = leadService.createLead(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<LeadResponse>> getAllLeads() {
        List<LeadResponse> responses = leadService.getAllLeads();
        return ResponseEntity.ok(responses);
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<LeadResponse> getLeadById(@PathVariable UUID id) {
        LeadResponse response = leadService.getLeadById(id);
        return ResponseEntity.ok(response);
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LeadResponse> updateLead(@PathVariable UUID id,
                                                   @RequestBody LeadRequest request) {
        LeadResponse response = leadService.updateLead(id, request);
        return ResponseEntity.ok(response);
    }

    // Admin only
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLead(@PathVariable UUID id) {
        leadService.deleteLead(id);
        return ResponseEntity.noContent().build();
    }
}
