package com.crm.educational_crm_backend.controller.timetable;

import com.crm.educational_crm_backend.dto.timetable.TimetableRequest;
import com.crm.educational_crm_backend.dto.timetable.TimetableResponse;
import com.crm.educational_crm_backend.service.timetable.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TimetableResponse> createOrUpdate(@RequestBody TimetableRequest request) {
        return ResponseEntity.ok(timetableService.createOrUpdateTimetable(request));
    }

    @GetMapping("/course/{courseId}")
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY','STUDENT')")
    public ResponseEntity<List<TimetableResponse>> getByCourse(@PathVariable UUID courseId) {
        return ResponseEntity.ok(timetableService.getTimetableByCourse(courseId));
    }

    @GetMapping("/faculty/{facultyId}")
    @PreAuthorize("hasAnyRole('ADMIN','FACULTY')")
    public ResponseEntity<List<TimetableResponse>> getByFaculty(@PathVariable UUID facultyId) {
        return ResponseEntity.ok(timetableService.getTimetableByFaculty(facultyId));
    }
}
