package com.prueba.demo.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.demo.dto.userDto;
import com.prueba.demo.dto.userTokeDto;
import com.prueba.demo.models.user;
import com.prueba.demo.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class authController {

  @Autowired
  private AuthenticationManager authManager;
  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private com.prueba.demo.services.userService userService;

  @PostMapping("/login")
  public ResponseEntity<userTokeDto> login(@RequestBody userDto userdto) {
    userTokeDto userToReturn = new userTokeDto();
    try {
      authManager.authenticate(
          new UsernamePasswordAuthenticationToken(userdto.getEmail(), userdto.getPassword()));
      user user=userService.getByEmail(userdto.getEmail());
      String token = jwtUtil.generateToken(user.getEmail());
      userToReturn.setUser(user);
      userToReturn.setToken(token);
    } catch (org.springframework.security.core.AuthenticationException e) {
      throw new RuntimeException("Email o contraseña inválidos");
    }
    return ResponseEntity.ok(userToReturn);
  }

  @PostMapping("/register")
  public user register(@RequestBody user newUser) {
    return userService.createUser(newUser);
  }

  @GetMapping("/isAuthorized")
  public ResponseEntity<Map<String, Object>> isAuthorized(@RequestHeader("Authorization") String token) {
    Map<String, Object> response = new HashMap<>();
    String tokenReal = token.substring(7);
    try {
      String email = jwtUtil.extractEmail(tokenReal);
      boolean isValid = jwtUtil.validateToken(tokenReal, email);
      response.put("valid", isValid);
      response.put("email", email);
      response.put("message", isValid ? "Token válido" : "Token inválido o expirado");
      return ResponseEntity.ok(response);
    } catch (io.jsonwebtoken.ExpiredJwtException e) {
        response.put("valid", false);
        response.put("message", "Token expirado");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

    } catch (SecurityException e) {
        // Aquí atrapamos tokens mal firmados
        response.put("valid", false);
        response.put("message", "Token inválido: firma incorrecta");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);

    } catch (Exception e) {
        response.put("valid", false);
        response.put("message", "Token inválido");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
  }
}
