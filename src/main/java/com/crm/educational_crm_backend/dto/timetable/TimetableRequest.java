package com.crm.educational_crm_backend.dto.timetable;

import com.crm.educational_crm_backend.entity.enums.DayOfWeekEnum;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class TimetableRequest {
    private UUID courseId;
    private UUID subjectId;
    private UUID facultyId;
    private DayOfWeekEnum dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;
    private String room;
}
