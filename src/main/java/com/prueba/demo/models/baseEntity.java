package com.prueba.demo.models;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;

@MappedSuperclass
public abstract class baseEntity {
@Column(name = "created_at", nullable = false, updatable = false)
protected Instant createdAt = Instant.now();


@Column(name = "updated_at", nullable = false)
protected Instant updatedAt = Instant.now();


@PreUpdate
protected void onUpdate() { this.updatedAt = Instant.now(); }


public Instant getCreatedAt() { return createdAt; }
public Instant getUpdatedAt() { return updatedAt; }
}