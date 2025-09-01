package com.prueba.demo.dto;

import java.time.Instant;

import com.prueba.demo.enums.checkAction;
import com.prueba.demo.enums.checkSource;

public class lastCheckForUser {
  public Long id;
  public String username;
  public String email;
  public String fullName;
  public String department;
  public checkAction checkAction;
  public checkSource checkSource;
  public Instant occuredAt;
  public com.prueba.demo.enums.role role;
  public boolean active;

  public lastCheckForUser(Long id, String username, String email, String fullName, String department,
      com.prueba.demo.enums.checkAction checkAction, com.prueba.demo.enums.checkSource checkSource, Instant occuredAt,
      com.prueba.demo.enums.role role, boolean active) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.fullName = fullName;
    this.department = department;
    this.checkAction = checkAction;
    this.checkSource = checkSource;
    this.occuredAt = occuredAt;
    this.role = role;
    this.active = active;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public com.prueba.demo.enums.role getRole() {
    return role;
  }

  public void setRole(com.prueba.demo.enums.role role) {
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getFullName() {
    return fullName;
  }

  public String getDepartment() {
    return department;
  }

  public checkAction getCheckAction() {
    return checkAction;
  }

  public checkSource getCheckSource() {
    return checkSource;
  }

  public Instant getOccuredAt() {
    return occuredAt;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public void setCheckAction(checkAction checkAction) {
    this.checkAction = checkAction;
  }

  public void setCheckSource(checkSource checkSource) {
    this.checkSource = checkSource;
  }

  public void setOccuredAt(Instant occuredAt) {
    this.occuredAt = occuredAt;
  }

}
