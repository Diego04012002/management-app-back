package com.prueba.demo.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prueba.demo.enums.checkAction;
import com.prueba.demo.enums.checkSource;
import com.prueba.demo.models.CheckLog;
import com.prueba.demo.models.user;
import com.prueba.demo.respositories.checkInRepository;

@Service
public class checkInService {

  @Autowired
  private checkInRepository checkinRepository;
  @Autowired
  private userService userService;

  public List<CheckLog> findAll() {
    return checkinRepository.findAll();
  }

  public CheckLog createCheckInLog(Long id) {
    CheckLog checkLog = checkinRepository.findFirstBySubjectIdOrderByOccurredAtDesc(id).orElse(null);
    CheckLog newCheckLog = new CheckLog();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (checkLog == null) {
      newCheckLog.setId(null);
      user user = userService.findById(id);
      newCheckLog.setSubject(user);
      user userPerfomed = userService.getByEmail(auth.getName());
      newCheckLog.setPerformedBy(userPerfomed);
      newCheckLog.setAction(checkAction.CHECK_IN);
      newCheckLog.setSource(checkSource.ADMIN_PANEL);
      newCheckLog.setOccurredAt(Instant.now());
      newCheckLog.setNotes("");
    } else {
      newCheckLog.setId(null);
      newCheckLog.setSubject(checkLog.getSubject());
      user userPerfomed = userService.getByEmail(auth.getName());
      newCheckLog.setPerformedBy(userPerfomed);
      newCheckLog.setOccurredAt(Instant.now());
      newCheckLog.setNotes("");
      newCheckLog.setSource(checkSource.ADMIN_PANEL);
      newCheckLog
          .setAction(checkLog.getAction() == checkAction.CHECK_IN ? checkAction.CHECK_OUT : checkAction.CHECK_IN);
    }

    return checkinRepository.save(newCheckLog);
  }

}
