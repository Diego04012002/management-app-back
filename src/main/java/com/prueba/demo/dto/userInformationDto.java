package com.prueba.demo.dto;

import java.time.Instant;

public class userInformationDto {
  public Long id;
  public String email;
  public String fullName;
  public boolean active;
  public com.prueba.demo.enums.role role;
  public Instant lastCheckIn;
  public Instant lastCheckOut;
  public String department;

  public userInformationDto(Long id, String email, String fullName, Boolean active, com.prueba.demo.enums.role role,
      Instant lastCheckIn,
      Instant lastCheckOut, String department) {
    this.id = id;
    this.email = email;
    this.fullName = fullName;
    this.active = active;
    this.role = role;
    this.lastCheckIn = lastCheckIn;
    this.lastCheckOut = lastCheckOut;
    this.department = department;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public String getFullName() {
    return fullName;
  }

  public boolean isActive() {
    return active;
  }

  public com.prueba.demo.enums.role getRole() {
    return role;
  }

  public Instant getLastCheckIn() {
    return lastCheckIn;
  }

  public Instant getLastCheckOut() {
    return lastCheckOut;
  }

  public String getDepartment() {
    return department;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public void setRole(com.prueba.demo.enums.role role) {
    this.role = role;
  }

  public void setLastCheckIn(Instant lastCheckIn) {
    this.lastCheckIn = lastCheckIn;
  }

  public void setLastCheckOut(Instant lastCheckOut) {
    this.lastCheckOut = lastCheckOut;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
