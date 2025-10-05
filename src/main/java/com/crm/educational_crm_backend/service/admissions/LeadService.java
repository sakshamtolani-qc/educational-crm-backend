package com.crm.educational_crm_backend.service.admissions;

import com.crm.educational_crm_backend.dto.admissions.LeadRequest;
import com.crm.educational_crm_backend.dto.admissions.LeadResponse;

import java.util.List;
import java.util.UUID;

public interface LeadService {

    LeadResponse createLead(LeadRequest request);

    LeadResponse getLeadById(UUID id);

    List<LeadResponse> getAllLeads();

    LeadResponse updateLead(UUID id, LeadRequest request);

    void deleteLead(UUID id);
}
