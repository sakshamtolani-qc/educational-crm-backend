package com.crm.educational_crm_backend.repository.exam;

import com.crm.educational_crm_backend.entity.exam.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExamRepository extends JpaRepository<Exam, UUID> {
}
