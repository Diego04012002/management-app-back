package com.prueba.demo.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.prueba.demo.dto.allChecksUser;
import com.prueba.demo.dto.lastCheckForUser;
import com.prueba.demo.dto.userInformationDto;
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
          c.occurredAt,
          u.role,
          u.active
      )
      FROM user u
      LEFT JOIN CheckLog c
          ON c.subject.id = u.id
         AND c.occurredAt = (
              SELECT MAX(c2.occurredAt)
              FROM CheckLog c2
              WHERE c2.subject.id = u.id
         )
      WHERE (:fullName IS NULL OR u.fullName LIKE %:fullName%)
      ORDER BY u.id ASC
                  """)
  Page<lastCheckForUser> findUsersWithLastCheckLog(Pageable pageable, @Param("fullName") String fullName);

  @Query("""
      SELECT new com.prueba.demo.dto.allChecksUser(
        c.occurredAt,
        u.fullName,
        u.role,
        c.action,
        u.department.name
      )
      FROM user u
      LEFT JOIN CheckLog c
        ON c.subject.id = u.id
      WHERE (:fullName IS NULL OR u.fullName LIKE %:fullName%)
      ORDER BY u.id ASC
      """)
  Page<allChecksUser> findUsersWithAllCheckLog(Pageable pageable, @Param("fullName") String fullName);

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

  @Query("""
      SELECT new com.prueba.demo.dto.userInformationDto(
          u.email,
          u.fullName,
          u.active,
          u.role,
          (
              SELECT MAX(c1.occurredAt)
              FROM CheckLog c1
              WHERE c1.subject.id = u.id AND c1.action = 'CHECK_IN'
          ),
          (
              SELECT MAX(c2.occurredAt)
              FROM CheckLog c2
              WHERE c2.subject.id = u.id AND (c2.action = 'CHECK_OUT' OR c2.action IS NULL)
          ),
          u.department.name
      )
      FROM user u
      WHERE u.email = :email
      """)
  Optional<userInformationDto> findLastCheckInOutByEmail(@Param("email") String email);

}
