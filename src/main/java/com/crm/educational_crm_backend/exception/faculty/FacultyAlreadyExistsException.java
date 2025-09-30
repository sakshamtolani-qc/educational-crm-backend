package com.crm.educational_crm_backend.exception.faculty;

public class FacultyAlreadyExistsException extends RuntimeException {

    public FacultyAlreadyExistsException() {
        super();
    }

    public FacultyAlreadyExistsException(String message) {
        super(message);
    }

    public FacultyAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
