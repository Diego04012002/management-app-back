package com.prueba.demo.respositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prueba.demo.models.CheckLog;

@Repository
public interface checkInRepository extends JpaRepository<CheckLog, Long> {
  @EntityGraph(attributePaths = { "subject", "subject.department" })

  Optional<CheckLog> findFirstBySubjectIdOrderByOccurredAtDesc(Long userId);

  @Query("SELECT COUNT(cl) FROM CheckLog cl WHERE FUNCTION('DATE', cl.occurredAt) = CURRENT_DATE")
  int countChecksLogsCurrentDay();

  @Query(value = """
      SELECT COUNT(DISTINCT cl.subject_user_id) AS active_users
      FROM check_logs cl
      WHERE cl.action = 'IN'
        AND NOT EXISTS (
          SELECT 1
          FROM check_logs cl2
          WHERE cl2.subject_user_id = cl.subject_user_id
            AND cl2.action = 'OUT'
            AND cl2.occurred_at > cl.occurred_at
        );""", nativeQuery = true)
  int countChecksInsCurrentDay();

}
