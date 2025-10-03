package com.crm.educational_crm_backend.exception.exam;

public class ExamAlreadyExistsException extends RuntimeException {
    public ExamAlreadyExistsException(String message) {
        super(message);
    }
}
