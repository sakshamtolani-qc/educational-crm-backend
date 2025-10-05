package com.crm.educational_crm_backend.service.admissions;

import com.crm.educational_crm_backend.dto.admissions.AdmissionRequest;
import com.crm.educational_crm_backend.dto.admissions.AdmissionResponse;

import java.util.List;
import java.util.UUID;

public interface AdmissionService {

    AdmissionResponse createAdmission(AdmissionRequest request);

    AdmissionResponse getAdmissionById(UUID id);

    List<AdmissionResponse> getAllAdmissions();

    AdmissionResponse approveAdmission(UUID id);

    AdmissionResponse rejectAdmission(UUID id);

    AdmissionResponse updateAdmission(UUID id, AdmissionRequest request);

    void deleteAdmission(UUID id);
}
