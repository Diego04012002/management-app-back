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

  public CheckLog createCheckInLog(Long id, boolean isAdmin) {
    CheckLog checkLog = checkinRepository.findFirstBySubjectIdOrderByOccurredAtDesc(id).orElse(null);
    CheckLog newCheckLog = new CheckLog();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (checkLog == null) {
      newCheckLog.setId(null);
      user user = userService.findById(id);
      newCheckLog.setSubject(user);
      if(isAdmin){
        user userPerfomed = userService.getByEmail(auth.getName());
        newCheckLog.setPerformedBy(userPerfomed);
        newCheckLog.setSource(checkSource.ADMIN_PANEL);
      }else if(!isAdmin){
        user userPerfomed = null;
        newCheckLog.setPerformedBy(userPerfomed);
        newCheckLog.setSource(checkSource.SELF_SERVICE);
      }
      newCheckLog.setAction(checkAction.CHECK_IN);
      newCheckLog.setOccurredAt(Instant.now());
      newCheckLog.setNotes("");
    } else {
      newCheckLog.setId(null);
      newCheckLog.setSubject(checkLog.getSubject());
      if(isAdmin){
        user userPerfomed = userService.getByEmail(auth.getName());
        newCheckLog.setPerformedBy(userPerfomed);
        newCheckLog.setSource(checkSource.ADMIN_PANEL);
      }else if(!isAdmin){
        user userPerfomed = null;
        newCheckLog.setPerformedBy(userPerfomed);
        newCheckLog.setSource(checkSource.SELF_SERVICE);
      }
      newCheckLog.setOccurredAt(Instant.now());
      newCheckLog.setNotes("");
      newCheckLog
          .setAction(checkLog.getAction() == checkAction.CHECK_IN ? checkAction.CHECK_OUT : checkAction.CHECK_IN);
    }

    return checkinRepository.save(newCheckLog);
  }

}
