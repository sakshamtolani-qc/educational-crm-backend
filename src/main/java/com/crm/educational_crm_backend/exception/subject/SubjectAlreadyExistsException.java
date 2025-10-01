package com.crm.educational_crm_backend.exception.subject;

public class SubjectAlreadyExistsException extends RuntimeException {

    public SubjectAlreadyExistsException() {
        super();
    }

    public SubjectAlreadyExistsException(String message) {
        super(message);
    }

    public SubjectAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
