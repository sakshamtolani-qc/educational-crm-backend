package com.crm.educational_crm_backend.repository.timetable;

import com.crm.educational_crm_backend.entity.timetable.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, UUID> {
    List<Timetable> findByCourseId(UUID courseId);
    List<Timetable> findByFacultyId(UUID facultyId);
}
