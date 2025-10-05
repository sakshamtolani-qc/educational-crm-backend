package com.crm.educational_crm_backend.service.admissions;

import com.crm.educational_crm_backend.dto.admissions.AdmissionRequest;
import com.crm.educational_crm_backend.dto.admissions.AdmissionResponse;
import com.crm.educational_crm_backend.entity.admissions.Admission;
import com.crm.educational_crm_backend.entity.enums.AdmissionStatus;
import com.crm.educational_crm_backend.repository.admissions.AdmissionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final AdmissionRepository admissionRepository;

    public AdmissionServiceImpl(AdmissionRepository admissionRepository) {
        this.admissionRepository = admissionRepository;
    }

    @Override
    public AdmissionResponse createAdmission(AdmissionRequest request) {
        Admission admission = Admission.builder()
                .leadId(request.getLeadId())
                .userId(request.getUserId())
                .courseId(request.getCourseId())
                .remarks(request.getRemarks())
                .documents(request.getDocuments())
                .status(AdmissionStatus.PENDING)
                .applicationDate(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Admission saved = admissionRepository.save(admission);
        return mapToResponse(saved);
    }

    @Override
    public AdmissionResponse getAdmissionById(UUID id) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
        return mapToResponse(admission);
    }

    @Override
    public List<AdmissionResponse> getAllAdmissions() {
        return admissionRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AdmissionResponse approveAdmission(UUID id) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
        admission.setStatus(AdmissionStatus.APPROVED);
        admission.setUpdatedAt(LocalDateTime.now());
        return mapToResponse(admissionRepository.save(admission));
    }

    @Override
    public AdmissionResponse rejectAdmission(UUID id) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
        admission.setStatus(AdmissionStatus.REJECTED);
        admission.setUpdatedAt(LocalDateTime.now());
        return mapToResponse(admissionRepository.save(admission));
    }

    @Override
    public AdmissionResponse updateAdmission(UUID id, AdmissionRequest request) {
        Admission admission = admissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admission not found"));
        admission.setLeadId(request.getLeadId());
        admission.setUserId(request.getUserId());
        admission.setCourseId(request.getCourseId());
        admission.setRemarks(request.getRemarks());
        admission.setDocuments(request.getDocuments());
        admission.setUpdatedAt(LocalDateTime.now());
        return mapToResponse(admissionRepository.save(admission));
    }

    @Override
    public void deleteAdmission(UUID id) {
        admissionRepository.deleteById(id);
    }

    private AdmissionResponse mapToResponse(Admission admission) {
        return AdmissionResponse.builder()
                .id(admission.getId())
                .leadId(admission.getLeadId())
                .userId(admission.getUserId())
                .courseId(admission.getCourseId())
                .remarks(admission.getRemarks())
                .documents(admission.getDocuments())
                .status(admission.getStatus())
                .applicationDate(admission.getApplicationDate())
                .createdAt(admission.getCreatedAt())
                .updatedAt(admission.getUpdatedAt())
                .build();
    }
}
