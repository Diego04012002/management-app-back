package com.prueba.demo.dto;


import com.prueba.demo.enums.checkAction;
import com.prueba.demo.enums.checkSource;

public class checkInDto {

  private checkAction action;
  private Long userId;
  private Long adminUserId;
  private checkSource checkSource;
  private String notes;

  public checkAction getAction() {
    return action;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getAdminUserId() {
    return adminUserId;
  }

  public checkSource getCheckSource() {
    return checkSource;
  }

  public String getNotes() {
    return notes;
  }

  public void setAction(checkAction action) {
    this.action = action;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setAdminUserId(Long adminUserId) {
    this.adminUserId = adminUserId;
  }

  public void setCheckSource(checkSource checkSource) {
    this.checkSource = checkSource;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

}
