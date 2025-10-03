package com.crm.educational_crm_backend.repository.results;

import com.crm.educational_crm_backend.entity.results.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResultRepository extends JpaRepository<Result, UUID> {
    List<Result> findByExamId(UUID examId);
    List<Result> findByStudentId(UUID studentId);
}
