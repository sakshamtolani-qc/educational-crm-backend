package com.crm.educational_crm_backend.repository.faculty;

import com.crm.educational_crm_backend.entity.faculty.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, UUID> {

    // Optional: find faculty by user ID
    Optional<Faculty> findByUserId(UUID userId);
    
    // Optional: check if faculty exists for a user
    boolean existsByUserId(UUID userId);
}
