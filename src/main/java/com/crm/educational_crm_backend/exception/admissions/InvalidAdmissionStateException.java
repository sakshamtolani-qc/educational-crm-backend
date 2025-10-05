package com.crm.educational_crm_backend.exception.admissions;

public class InvalidAdmissionStateException extends RuntimeException {
    public InvalidAdmissionStateException(String message) {
        super(message);
    }
}
