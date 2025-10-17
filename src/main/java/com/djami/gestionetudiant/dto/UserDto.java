package com.djami.gestionetudiant.dto;

import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private List<String> roles;
    private boolean enabled;

    // Constructeurs
    public UserDto() {}

    public UserDto(Long id, String username, List<String> roles, boolean enabled) {
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.enabled = enabled;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}