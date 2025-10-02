package com.crm.educational_crm_backend.controller.fee;

import com.crm.educational_crm_backend.dto.fee.FeeRequest;
import com.crm.educational_crm_backend.dto.fee.FeeResponse;
import com.crm.educational_crm_backend.service.fee.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class FeeController {

    @Autowired
    private FeeService feeService;

    // Create a new fee record (Admin only)
    // This will allow for multiple records because student may have many fees to pay.
    // Admin needs to care of it.
    @PostMapping("/fees")
    @PreAuthorize("hasRole('ADMIN')")
    public FeeResponse createFee(@RequestBody FeeRequest feeRequest) {
        return feeService.createFee(feeRequest);
    }

    // Get all fee records for a student (Admin / Student)
    @GetMapping("/fees/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isCurrentStudent(#studentId)")
    public List<FeeResponse> getFeesByStudent(@PathVariable UUID studentId) {
        return feeService.getFeesByStudentId(studentId);
    }

    // Record a payment for a fee (Admin / Student)
    // Making a payment as PAID and save date as today.
    @PostMapping("/payments")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isFeeOwnedByCurrentStudent(#feeId)")
    public FeeResponse recordPayment(@RequestParam String feeId) {
        UUID uuid;
        try {
            uuid = UUID.fromString(feeId.trim()); 
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid feeId: " + feeId);
        }
        return feeService.recordPayment(uuid);
    }

    // Get payment details by fee ID (Admin / Student)
    @GetMapping("/payments/{id}")
    @PreAuthorize("hasRole('ADMIN') or @securityService.isFeeOwnedByCurrentStudent(#id)")
    public FeeResponse getPaymentDetails(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id.trim()); // safe conversion
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid feeId: " + id);
        }
        return feeService.getPaymentDetails(uuid);
    }

}
