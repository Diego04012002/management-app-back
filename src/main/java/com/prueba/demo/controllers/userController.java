package com.prueba.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.dto.lastCheckForUser;
import com.prueba.demo.dto.numberChecksLogs;
import com.prueba.demo.models.user;
import com.prueba.demo.services.userService;

@RestController
@RequestMapping("/api")
public class userController {

  @Autowired
  private userService userService;

  @GetMapping("/users")
  public List<user> getUsers() {
    return userService.getAll();
  }

  @PostMapping(value="/users" , consumes = "application/json", produces = "application/json")
  public ResponseEntity<user> createUser(@RequestBody user newUser) {
    return ResponseEntity.ok(userService.createUser(newUser));
  }

  @GetMapping("getUsersWithLastLog")
  public Page<lastCheckForUser> getUsersWithLastLog(Pageable pageable) {
    return userService.getUserWithLastCheck(pageable);
  }

  @GetMapping("getCountUserWithCheckInAndCheckOut")
  public ResponseEntity<numberChecksLogs> getUsersWithLastLogNumber() {
    return ResponseEntity.ok(userService.getCountUserWithCheckInAndCheckOut());
  }
}
