package com.crm.educational_crm_backend.service.user;

import com.crm.educational_crm_backend.dto.user.UserResponse;
import com.crm.educational_crm_backend.entity.user.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    // Get all users (returns entity list)
    List<User> getAllUsers();

    // Get user by ID
    Optional<User> getUserById(UUID id);

    // Update user profile
    User updateUser(User user);

    // Delete user
    void deleteUser(UUID id);

    // Mapper: User -> UserResponse DTO
    UserResponse mapToUserResponse(User user);

    // Get all users mapped to DTO
    List<UserResponse> getAllUsersResponse();
}
