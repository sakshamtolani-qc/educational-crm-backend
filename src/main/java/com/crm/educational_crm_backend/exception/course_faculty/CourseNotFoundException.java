package com.crm.educational_crm_backend.exception.course_faculty;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
