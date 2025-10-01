package com.crm.educational_crm_backend.service.subject;

import com.crm.educational_crm_backend.dto.subject.SubjectRequest;
import com.crm.educational_crm_backend.dto.subject.SubjectResponse;
import java.util.List;
public interface SubjectService {
    SubjectResponse createSubject(SubjectRequest request);
    List<SubjectResponse> getAllSubjects();
}
