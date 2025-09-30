package com.crm.educational_crm_backend.service.faculty;

import com.crm.educational_crm_backend.dto.faculty.FacultyRequest;
import com.crm.educational_crm_backend.dto.faculty.FacultyResponse;

import java.util.List;
import java.util.UUID;

public interface FacultyService {

    FacultyResponse createFaculty(FacultyRequest request);

    List<FacultyResponse> getAllFaculty();

    FacultyResponse getFacultyById(UUID id);

    FacultyResponse updateFaculty(UUID id, FacultyRequest request);

    void deleteFaculty(UUID id);
}
