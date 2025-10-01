package com.crm.educational_crm_backend.repository.subject;

import com.crm.educational_crm_backend.entity.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, UUID> {
    boolean existsByName(String name);
    boolean existsByCode(String code);
}
