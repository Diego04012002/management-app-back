package com.prueba.demo.models;

import java.time.Instant;

import com.prueba.demo.enums.checkAction;
import com.prueba.demo.enums.checkSource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "check_logs", indexes = {
    @Index(name = "idx_check_logs_subject_time", columnList = "subject_user_id, occurred_at DESC"),
    @Index(name = "idx_check_logs_performed_by", columnList = "performed_by_user_id"),
    @Index(name = "idx_check_logs_action", columnList = "action")
})
public class CheckLog extends baseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  @JoinColumn(name = "subject_user_id", nullable = false)
  private user subject;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "performed_by_user_id", nullable = true)
  private user performedBy;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 16)
  private checkAction action;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 16)
  private checkSource source = checkSource.SELF_SERVICE;

  @Column(name = "occurred_at", nullable = false)
  private Instant occurredAt = Instant.now();

  @Column(length = 500)
  private String notes;

  // --- Getters & Setters ---
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public user getSubject() {
    return subject;
  }

  public void setSubject(user subject) {
    this.subject = subject;
  }

  public user getPerformedBy() {
    return performedBy;
  }

  public void setPerformedBy(user performedBy) {
    this.performedBy = performedBy;
  }

  public checkAction getAction() {
    return action;
  }

  public void setAction(checkAction action) {
    this.action = action;
  }

  public checkSource getSource() {
    return source;
  }

  public void setSource(checkSource source) {
    this.source = source;
  }

  public Instant getOccurredAt() {
    return occurredAt;
  }

  public void setOccurredAt(Instant occurredAt) {
    this.occurredAt = occurredAt;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }
}
