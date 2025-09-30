package com.crm.educational_crm_backend.exception.student;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException() {
        super();
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
