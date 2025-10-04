package com.crm.educational_crm_backend.service.timetable;

import com.crm.educational_crm_backend.dto.timetable.TimetableRequest;
import com.crm.educational_crm_backend.dto.timetable.TimetableResponse;

import java.util.List;
import java.util.UUID;

public interface TimetableService {
    TimetableResponse createOrUpdateTimetable(TimetableRequest request);
    List<TimetableResponse> getTimetableByCourse(UUID courseId);
    List<TimetableResponse> getTimetableByFaculty(UUID facultyId);
}
