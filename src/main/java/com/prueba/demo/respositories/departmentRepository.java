package com.prueba.demo.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.demo.models.department;

@Repository
public interface departmentRepository extends JpaRepository<department, Long> {
  
}
