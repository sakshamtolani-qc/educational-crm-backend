package com.crm.educational_crm_backend.entity.course_faculty;

import com.crm.educational_crm_backend.entity.course.Course;
import com.crm.educational_crm_backend.entity.faculty.Faculty;
import com.crm.educational_crm_backend.entity.enums.FacultyRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "course_faculty")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseFaculty {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private FacultyRole role = FacultyRole.COORDINATOR;

    @Column(nullable = false)
    @Builder.Default
    private LocalDateTime assignedAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (role == null) role = FacultyRole.COORDINATOR;
        if (assignedAt == null) assignedAt = LocalDateTime.now();
    }
}
