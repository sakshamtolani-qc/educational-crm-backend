package com.crm.educational_crm_backend.entity.student;

import com.crm.educational_crm_backend.entity.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "enrollment_no", nullable = false, unique = true)
    private String enrollmentNo;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "address")
    private String address;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDate.now();
    }
}
