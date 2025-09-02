package com.prueba.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.models.CheckLog;
import com.prueba.demo.services.checkInService;

@RestController
@RequestMapping("/api")
public class checkInController {

  @Autowired
  private checkInService checkInService;

  @GetMapping("/getCheckIns")
  public ResponseEntity<List<CheckLog>> getCheckIns() {
    return ResponseEntity.ok(checkInService.findAll());
  }

  @PostMapping("checkInByAdmin/{userId}")
  public ResponseEntity<CheckLog> checkIn(@PathVariable Long userId) {
    return ResponseEntity.ok(checkInService.createCheckInLog(userId, true));
  }

  @PostMapping("checkInYourself/{userId}")
  public ResponseEntity<CheckLog> checkInYourself(@PathVariable Long userId) {
    return ResponseEntity.ok(checkInService.createCheckInLog(userId, false));
  }
}
