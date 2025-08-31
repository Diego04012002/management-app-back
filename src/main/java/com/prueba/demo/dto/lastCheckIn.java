package com.prueba.demo.dto;

import java.time.Instant;

public class lastCheckIn {
  private Long id;
  private String recordType;
  private String action;
  private Instant createdAt;
  private String location;

  public lastCheckIn(Long id, String recordType, String action, Instant createdAt, String location) {
    this.id = id;
    this.recordType = recordType;
    this.action = action;
    this.createdAt = createdAt;
    this.location = location;
  }
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }

  public String getRecordType() {
    return recordType;
  }
  
  public void setRecordType(String recordType) {
    this.recordType = recordType;
  }

  public String getAction() {
    return action;
  }
  
  public void setAction(String action) {
    this.action = action;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  
}
