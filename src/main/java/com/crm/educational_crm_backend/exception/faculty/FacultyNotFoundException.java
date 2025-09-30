package com.crm.educational_crm_backend.exception.faculty;

public class FacultyNotFoundException extends RuntimeException {

    public FacultyNotFoundException() {
        super();
    }

    public FacultyNotFoundException(String message) {
        super(message);
    }

    public FacultyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
