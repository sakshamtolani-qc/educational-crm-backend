package com.crm.educational_crm_backend.repository;

import com.crm.educational_crm_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
   
    // Add this method so AuthService can call it
    Optional<User> findByEmail(String email);
}
