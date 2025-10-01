package com.crm.educational_crm_backend.exception.course;

public class CourseAlreadyExistsException extends RuntimeException {

    public CourseAlreadyExistsException() {
        super();
    }

    public CourseAlreadyExistsException(String message) {
        super(message);
    }

    public CourseAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
