package com.crm.educational_crm_backend.service.results;

import com.crm.educational_crm_backend.dto.results.ResultRequest;
import com.crm.educational_crm_backend.entity.results.Result;

import java.util.List;
import java.util.UUID;

public interface ResultService {
    Result addResult(UUID examId, ResultRequest dto);
    List<Result> getResultsByExam(UUID examId);
    List<Result> getResultsByStudent(UUID studentId);
    Result updateResult(UUID resultId, ResultRequest dto);

}
