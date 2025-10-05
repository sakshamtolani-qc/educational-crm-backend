package com.crm.educational_crm_backend.repository.admissions;

import com.crm.educational_crm_backend.entity.admissions.Lead;
import com.crm.educational_crm_backend.entity.enums.LeadStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LeadRepository extends JpaRepository<Lead, UUID> {

    // Find all leads by status
    List<Lead> findByStatus(LeadStatus status);

    // Optional: find by interested course
    List<Lead> findByInterestedCourseId(UUID courseId);
}
