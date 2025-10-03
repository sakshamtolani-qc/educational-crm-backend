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

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already registered: " + user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.STUDENT);

        return userRepository.save(user);
    }

    public Optional<AuthResponse> login(String email, String rawPassword) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword())) {
            User user = userOpt.get();
            String token = jwtTokenProvider.generateToken(user.getEmail(), user.getId(), user.getRole().name());

            return Optional.of(AuthResponse.builder()
                    .token(token)
                    .username(user.getUsername())
                    .role(user.getRole().name())
                    .id(user.getId())
                    .build()
            );
        }

        return Optional.empty();
    }
}
