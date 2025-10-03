package com.crm.educational_crm_backend.service.user;

import com.crm.educational_crm_backend.dto.user.UserResponse;
import com.crm.educational_crm_backend.entity.user.User;
import com.crm.educational_crm_backend.exception.user.UserNotFoundException;
import com.crm.educational_crm_backend.repository.user.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + user.getId()));

        existing = User.builder()
                .id(existing.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .gender(user.getGender())
                .role(user.getRole())
                .password(user.getPassword() != null && !user.getPassword().isBlank() 
                          ? passwordEncoder.encode(user.getPassword()) 
                          : existing.getPassword())
                .build();

        return userRepository.save(existing);
    }

    @Override
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole().name()
        );
    }

    @Override
    public List<UserResponse> getAllUsersResponse() {
        return getAllUsers().stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }
}
