package com.crm.educational_crm_backend.service.admissions;

import com.crm.educational_crm_backend.dto.admissions.LeadRequest;
import com.crm.educational_crm_backend.dto.admissions.LeadResponse;
import com.crm.educational_crm_backend.entity.admissions.Lead;
import com.crm.educational_crm_backend.exception.admissions.LeadNotFoundException;
import com.crm.educational_crm_backend.repository.admissions.LeadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    @Override
    public LeadResponse createLead(LeadRequest request) {
        Lead lead = Lead.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .interestedCourseId(request.getInterestedCourseId())
                .notes(request.getNotes())
                .build();

        Lead saved = leadRepository.save(lead);
        return mapToResponse(saved);
    }

    @Override
    public LeadResponse getLeadById(UUID id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new LeadNotFoundException("Lead not found with id: " + id));
        return mapToResponse(lead);
    }

    @Override
    public List<LeadResponse> getAllLeads() {
        return leadRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LeadResponse updateLead(UUID id, LeadRequest request) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new LeadNotFoundException("Lead not found with id: " + id));

        lead.setName(request.getName());
        lead.setEmail(request.getEmail());
        lead.setPhone(request.getPhone());
        lead.setInterestedCourseId(request.getInterestedCourseId());
        lead.setNotes(request.getNotes());

        return mapToResponse(leadRepository.save(lead));
    }

    @Override
    public void deleteLead(UUID id) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new LeadNotFoundException("Lead not found with id: " + id));
        leadRepository.delete(lead);
    }

    private LeadResponse mapToResponse(Lead lead) {
        return LeadResponse.builder()
                .id(lead.getId())
                .name(lead.getName())
                .email(lead.getEmail())
                .phone(lead.getPhone())
                .interestedCourseId(lead.getInterestedCourseId())
                .status(lead.getStatus())
                .notes(lead.getNotes())
                .createdAt(lead.getCreatedAt())
                .updatedAt(lead.getUpdatedAt())
                .build();
    }
}
