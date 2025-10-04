package com.crm.educational_crm_backend.dto.timetable;

import com.crm.educational_crm_backend.entity.enums.DayOfWeekEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TimetableResponse {
    private UUID id;
    private UUID courseId;
    private UUID subjectId;
    private UUID facultyId;
    private DayOfWeekEnum dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
