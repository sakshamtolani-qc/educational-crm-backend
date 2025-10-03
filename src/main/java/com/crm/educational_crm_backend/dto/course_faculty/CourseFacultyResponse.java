package com.crm.educational_crm_backend.dto.course_faculty;

import com.crm.educational_crm_backend.entity.enums.FacultyRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CourseFacultyResponse {
    private UUID id;
    private UUID courseId;
    private UUID facultyId;
    private FacultyRole role;
    private LocalDateTime assignedAt;
}
