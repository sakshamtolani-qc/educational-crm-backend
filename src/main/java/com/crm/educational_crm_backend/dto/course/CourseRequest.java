package com.crm.educational_crm_backend.dto.course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {

    private String name;
    private String code;
    private String description;
    private int credits;
    private int durationMonths;

}
