package com.crm.educational_crm_backend.exception.fee;

import java.util.UUID;

public class FeeNotFoundException extends RuntimeException {

    // Constructor with custom message
    public FeeNotFoundException(String message) {
        super(message);
    }

    // Optional: Constructor with UUID
    public FeeNotFoundException(UUID feeId) {
        super("Fee not found with id: " + feeId);
    }
}
