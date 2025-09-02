package com.prueba.demo.dto;

public class departmentDto {
  public departmentDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long id;
  public String name;

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
}
