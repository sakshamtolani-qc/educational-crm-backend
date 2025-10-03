package com.crm.educational_crm_backend.dto.course;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    private String id;
    private String name;
    private String code;
    private String description;
    private int credits;
    private int durationMonths;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
