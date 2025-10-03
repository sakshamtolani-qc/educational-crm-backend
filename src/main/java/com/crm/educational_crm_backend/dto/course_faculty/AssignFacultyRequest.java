package com.crm.educational_crm_backend.dto.course_faculty;

import com.crm.educational_crm_backend.entity.enums.FacultyRole;
import lombok.Data;

import java.util.UUID;

@Data
public class AssignFacultyRequest {
    private UUID facultyId;
    private FacultyRole role;  // optional, default is COORDINATOR
}
