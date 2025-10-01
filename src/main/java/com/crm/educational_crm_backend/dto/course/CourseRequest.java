package com.crm.educational_crm_backend.dto.course;

public class CourseRequest {

    private String name;
    private String code;
    private String description;
    private int credits;
    private int durationMonths;

    // Constructors
    public CourseRequest() {}

    public CourseRequest(String name, String code, String description, int credits, int durationMonths) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.credits = credits;
        this.durationMonths = durationMonths;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public int getDurationMonths() { return durationMonths; }
    public void setDurationMonths(int durationMonths) { this.durationMonths = durationMonths; }
}
