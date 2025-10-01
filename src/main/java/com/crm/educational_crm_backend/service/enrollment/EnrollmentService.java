package com.crm.educational_crm_backend.service.enrollment;

import com.crm.educational_crm_backend.dto.enrollment.EnrollmentRequest;
import com.crm.educational_crm_backend.dto.enrollment.EnrollmentResponse;
import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse enrollStudent(EnrollmentRequest request);
    List<EnrollmentResponse> getAllEnrollments();
}
