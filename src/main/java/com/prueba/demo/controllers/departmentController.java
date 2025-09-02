package com.prueba.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.dto.departmentDto;
import com.prueba.demo.models.department;
import com.prueba.demo.services.departmentService;

@RestController
@RequestMapping("/api")
public class departmentController {
  @Autowired
  private departmentService departmentServices;

  @GetMapping("/departments")
  public List<departmentDto> getDepartments() {
    return departmentServices.findDepartmentDto();
  }

  @PostMapping(value = "/departments", consumes = "application/json", produces = "application/json")
  public ResponseEntity<department> createDepartment(@RequestBody department department) {
    department saved = departmentServices.create(department);
    return ResponseEntity.ok(saved);
  }

}
