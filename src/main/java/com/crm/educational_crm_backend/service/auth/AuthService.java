package com.crm.educational_crm_backend.service.auth;

import com.crm.educational_crm_backend.dto.auth.AuthResponse;
import com.crm.educational_crm_backend.entity.enums.Role;
import com.crm.educational_crm_backend.entity.user.User;
import com.crm.educational_crm_backend.exception.user.EmailAlreadyExistsException;
import com.crm.educational_crm_backend.repository.user.UserRepository;
import com.crm.educational_crm_backend.security.jwt.JwtTokenProvider;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Register a new user with default STUDENT role.
     * Throws EmailAlreadyExistsException if email is already registered.
     */
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already registered: " + user.getEmail());
        }

        // Hash the password before storing
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role
        user.setRole(Role.STUDENT);

        return userRepository.save(user);
    }

    /**
     * Login user and generate JWT if credentials are valid.
     */
    public Optional<AuthResponse> login(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword())) {
            User user = userOpt.get();

            // Generate JWT using user email, ID, and role
            String token = jwtTokenProvider.generateToken(user.getEmail(), user.getId(), user.getRole().name());

            return Optional.of(new AuthResponse(
    token,
    user.getUsername(),
    user.getRole().name(),
    user.getId()  
    ));

        }

        return Optional.empty();
    }
}
