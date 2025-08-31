package com.prueba.demo.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.prueba.demo.dto.lastCheckForUser;
import com.prueba.demo.models.user;

@Repository
public interface usersRespository extends JpaRepository<user, Long> {
  Optional<user> findByEmail(String email);

  @Query("""
      SELECT new com.prueba.demo.dto.lastCheckForUser(
        u.id,
        u.username,
        u.email,
        u.fullName,
        u.department.name,
        c.action,
        c.source,
        c.occurredAt
      )
      FROM user u
      LEFT JOIN CheckLog c
        ON c.subject.id = u.id
       AND c.occurredAt = (
            SELECT MAX(c2.occurredAt)
            FROM CheckLog c2
            WHERE c2.subject.id = u.id
       )
      ORDER BY u.id ASC
      """)
  Page<lastCheckForUser> findUsersWithLastCheckLog(Pageable pageable);

  @Query("""
          SELECT COUNT(u)
          FROM user u
          LEFT JOIN CheckLog c
            ON c.subject.id = u.id
           AND c.occurredAt = (
                SELECT MAX(c2.occurredAt)
                FROM CheckLog c2
                WHERE c2.subject.id = u.id
           )
          WHERE c.action = 'CHECK_IN'
      """)
  long countUsersWithLastCheckIn();

  @Query("""
          SELECT COUNT(u)
          FROM user u
          LEFT JOIN CheckLog c
            ON c.subject.id = u.id
           AND c.occurredAt = (
                SELECT MAX(c2.occurredAt)
                FROM CheckLog c2
                WHERE c2.subject.id = u.id
           )
          WHERE c.action = 'CHECK_OUT' OR
          c.action IS NULL
      """)
  long countUsersWithLastCheckOut();
}
