package com.prueba.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.demo.dto.departmentDto;
import com.prueba.demo.models.department;
import com.prueba.demo.respositories.departmentRepository;


@Service
public class departmentService {

  @Autowired
  private departmentRepository departmentRepository;

  
  public List<departmentDto> findDepartmentDto() {
    return departmentRepository.findDepartmentDto();
  }

  public department create(department department) {
    return departmentRepository.save(department);
  }
}
