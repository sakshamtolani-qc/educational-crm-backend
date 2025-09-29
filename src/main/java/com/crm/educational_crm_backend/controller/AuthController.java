package com.crm.educational_crm_backend.controller;

import com.crm.educational_crm_backend.dto.AuthResponse;
import com.crm.educational_crm_backend.dto.LoginRequest;
import com.crm.educational_crm_backend.dto.RegisterRequest;
import com.crm.educational_crm_backend.dto.UserResponse;
import com.crm.educational_crm_backend.entity.Role;
import com.crm.educational_crm_backend.entity.User;
import com.crm.educational_crm_backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    user.setPassword(request.getPassword());
    user.setGender(request.getGender());
    user.setRole(Role.STUDENT); 

    User registeredUser = authService.register(user);

    UserResponse response = new UserResponse(
            registeredUser.getId(),
            registeredUser.getUsername(),
            registeredUser.getEmail(),
            registeredUser.getFirstName(),
            registeredUser.getLastName(),
            registeredUser.getRole().name() 
    );

    return ResponseEntity.ok(response);
}


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<AuthResponse> loginOpt = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        if (loginOpt.isPresent()) {
            return ResponseEntity.ok(loginOpt.get());
        } else {
            return ResponseEntity.status(401).body(new ErrorResponse("Invalid credentials"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }

    static class ErrorResponse {
        private String message;
        public ErrorResponse(String message) { this.message = message; }
        public String getMessage() { return message; }
    }
}
