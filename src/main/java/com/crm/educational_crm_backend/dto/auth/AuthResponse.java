package com.crm.educational_crm_backend.dto.auth;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private String username;
    private String role;
    private UUID id; // change from Long to UUID

    public AuthResponse(String token, String username, String role, UUID id) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.id = id;
    }

    // getters and setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
}
