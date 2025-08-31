package com.prueba.demo.respositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.demo.models.CheckLog;

@Repository
public interface checkInRepository extends JpaRepository<CheckLog, Long> {
  @EntityGraph(attributePaths = { "subject", "subject.department" })

  Optional<CheckLog> findFirstBySubjectIdOrderByOccurredAtDesc(Long userId);

}
