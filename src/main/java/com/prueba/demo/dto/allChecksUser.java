package com.prueba.demo.dto;

import java.time.Instant;

import com.prueba.demo.enums.checkAction;
import com.prueba.demo.enums.role;

public class allChecksUser {
  public Instant occuredAt;
  public String fullName;
  public role role;
  public checkAction checkAction;
  public String department;

  public allChecksUser(Instant occuredAt, String fullName, com.prueba.demo.enums.role role,
      com.prueba.demo.enums.checkAction checkAction, String department) {
    this.occuredAt = occuredAt;
    this.fullName = fullName;
    this.role = role;
    this.checkAction = checkAction;
    this.department = department;
  }

  public Instant getOccuredAt() {
    return occuredAt;
  }

  public void setOccuredAt(Instant occuredAt) {
    this.occuredAt = occuredAt;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public role getRole() {
    return role;
  }

  public void setRole(role role) {
    this.role = role;
  }

  public checkAction getCheckAction() {
    return checkAction;
  }

  public void setCheckAction(checkAction checkAction) {
    this.checkAction = checkAction;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }
}
