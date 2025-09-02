package com.prueba.demo.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prueba.demo.dto.departmentDto;
import com.prueba.demo.models.department;

@Repository
public interface departmentRepository extends JpaRepository<department, Long> {
  @Query("SELECT new com.prueba.demo.dto.departmentDto(d.id, d.name) FROM department d")
  List<departmentDto> findDepartmentDto();
}
