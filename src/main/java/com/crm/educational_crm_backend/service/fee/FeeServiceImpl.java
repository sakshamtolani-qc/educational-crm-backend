package com.crm.educational_crm_backend.service.fee;

import com.crm.educational_crm_backend.dto.fee.FeeRequest;
import com.crm.educational_crm_backend.dto.fee.FeeResponse;
import com.crm.educational_crm_backend.entity.fee.Fee;
import com.crm.educational_crm_backend.exception.fee.FeeNotFoundException;
import com.crm.educational_crm_backend.repository.fee.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FeeServiceImpl implements FeeService {

    @Autowired
    private FeeRepository feeRepository;

    @Override
    public FeeResponse createFee(FeeRequest feeRequest) {
        Fee fee = Fee.builder()
                .studentId(feeRequest.getStudentId())
                .courseId(feeRequest.getCourseId())
                .amount(feeRequest.getAmount())
                .dueDate(feeRequest.getDueDate())
                .build();
        Fee saved = feeRepository.save(fee);
        return mapToResponse(saved);
    }

    @Override
    public List<FeeResponse> getFeesByStudentId(UUID studentId) {
        List<Fee> fees = feeRepository.findByStudentId(studentId);
        return fees.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public FeeResponse recordPayment(UUID feeId) {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new FeeNotFoundException(feeId));

        if ("Paid".equalsIgnoreCase(fee.getStatus())) {
            throw new RuntimeException("Fee already paid on: " + fee.getPaidDate());
        }

        fee.setStatus("Paid");
        fee.setPaidDate(LocalDate.now());
        fee.setUpdatedAt(LocalDateTime.now());
        feeRepository.save(fee);

        return mapToResponse(fee);
    }

    @Override
    public FeeResponse getPaymentDetails(UUID feeId) {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new FeeNotFoundException("Fee not found with id: " + feeId));
        return mapToResponse(fee);
    }

    private FeeResponse mapToResponse(Fee fee) {
        FeeResponse response = new FeeResponse();
        response.setId(fee.getId());
        response.setStudentId(fee.getStudentId());
        response.setCourseId(fee.getCourseId());
        response.setAmount(fee.getAmount());
        response.setDueDate(fee.getDueDate());
        response.setPaidDate(fee.getPaidDate());
        response.setStatus(fee.getStatus());
        response.setCreatedAt(fee.getCreatedAt());
        response.setUpdatedAt(fee.getUpdatedAt());
        return response;
    }
}
