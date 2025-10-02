package com.crm.educational_crm_backend.service.fee;

import com.crm.educational_crm_backend.dto.fee.FeeRequest;
import com.crm.educational_crm_backend.dto.fee.FeeResponse;
import java.util.List;
import java.util.UUID;

public interface FeeService {
    FeeResponse createFee(FeeRequest feeRequest);
    List<FeeResponse> getFeesByStudentId(UUID studentId);
    FeeResponse recordPayment(UUID feeId);
    FeeResponse getPaymentDetails(UUID feeId);
}
