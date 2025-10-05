package com.crm.educational_crm_backend.repository.admissions;

import com.crm.educational_crm_backend.entity.admissions.Admission;
import com.crm.educational_crm_backend.entity.enums.AdmissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, UUID> {

    List<Admission> findByCourseId(UUID courseId);

    List<Admission> findByStatus(AdmissionStatus status);

    List<Admission> findByLeadId(UUID leadId);
}
