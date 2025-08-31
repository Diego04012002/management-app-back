package com.prueba.demo.dto;

import java.time.Instant;

public class userLastCheckIn {
    private Long id;
    private String name;
    private String email;
    private String position;
    private String role;
    private String status;
    private Instant createdAt;
    private lastCheckIn lastCheckin;

    public userLastCheckIn(Long id, String name, String email, String position,
            String role, String status, Instant createdAt,
            lastCheckIn lastCheckin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.lastCheckin = lastCheckin;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public lastCheckIn getLastCheckin() {
        return lastCheckin;
    }

    public void setLastCheckin(lastCheckIn lastCheckin) {
        this.lastCheckin = lastCheckin;
    }

}
