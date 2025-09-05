package com.prueba.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.dto.changePasswordDto;
import com.prueba.demo.dto.userDto;
import com.prueba.demo.dto.userTokeDto;
import com.prueba.demo.models.user;
import com.prueba.demo.services.authService;

@RestController
@RequestMapping("/auth")
public class authController {
  @Autowired
  private authService authService;
  @Autowired
  private com.prueba.demo.services.userService userService;

  @PostMapping("/login")
  public ResponseEntity<userTokeDto> login(@RequestBody userDto userdto) {
    try {
      return ResponseEntity.ok(authService.login(userdto));
    } catch (org.springframework.security.core.AuthenticationException e) {
      throw new RuntimeException("Email o contraseña inválidos");
    }
  }

  @PostMapping("/register")
  public user register(@RequestBody user newUser) {
    return userService.createUser(newUser);
  }

  @PostMapping("/changePassword")
  public ResponseEntity<Map<String, Object>> changePassword(@RequestBody  changePasswordDto changePasswordDto) {
    return authService.changePassword(changePasswordDto);
  }
    

  @GetMapping("/isAuthorized")
  public ResponseEntity<Map<String, Object>> isAuthorized(@RequestHeader("Authorization") String token) {
    return authService.isAuthorized(token);
  }
}
