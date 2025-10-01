package com.crm.educational_crm_backend.exception.subject;

public class SubjectNotFoundException extends RuntimeException {

    public SubjectNotFoundException() {
        super();
    }

    public SubjectNotFoundException(String message) {
        super(message);
    }

    public SubjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
