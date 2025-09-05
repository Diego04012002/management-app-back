package com.prueba.demo.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prueba.demo.dto.changePasswordDto;
import com.prueba.demo.dto.userDto;
import com.prueba.demo.dto.userTokeDto;
import com.prueba.demo.models.user;
import com.prueba.demo.respositories.usersRespository;
import com.prueba.demo.utils.JwtUtil;

@Service
public class authService {
  @Autowired
  private AuthenticationManager authManager;
  @Autowired
  private JwtUtil jwtUtil;
  @Autowired
  private com.prueba.demo.services.userService userService;
  @Autowired
  private usersRespository usersRespository;
  @Autowired
  private PasswordEncoder passwordEncoderService;

  public userTokeDto login(userDto userdto) {
    userTokeDto userToReturn = new userTokeDto();

    authManager.authenticate(new UsernamePasswordAuthenticationToken(userdto.getEmail(), userdto.getPassword()));
    user user = userService.getByEmail(userdto.getEmail());
    String token = jwtUtil.generateToken(user.getEmail());
    userToReturn.setUser(user);
    userToReturn.setToken(token);
    return userToReturn;
  }

  public ResponseEntity<Map<String, Object>> isAuthorized(String token) {
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

  public ResponseEntity<Map<String, Object>> changePassword(changePasswordDto dto) {
    Map<String, Object> response = new HashMap<>();

    user user = usersRespository.findByEmail(dto.getEmail()).orElse(null);
    if (user == null) {
      response.put("success", false);
      response.put("message", "Usuario no encontrado");
      return ResponseEntity.badRequest().body(response);
    }

    if (!passwordEncoderService.matches(dto.getCurrentPassword(), user.getPassword())) {
      response.put("success", false);
      response.put("message", "La contraseña actual es incorrecta");
      return ResponseEntity.badRequest().body(response);
    }

    if (passwordEncoderService.matches(dto.getNewPassword(), user.getPassword())) {
      response.put("success", false);
      response.put("message", "La nueva contraseña no puede ser la misma que la actual");
      return ResponseEntity.badRequest().body(response);
    }

    if (!dto.getNewPassword().equals(dto.getConfirmPassword())) {
      response.put("success", false);
      response.put("message", "Las contraseñas no coinciden");
      return ResponseEntity.badRequest().body(response);
    }

    user.setPassword(passwordEncoderService.encode(dto.getNewPassword()));
    usersRespository.save(user);

    response.put("success", true);
    response.put("message", "Contraseña cambiada exitosamente");

    return ResponseEntity.ok(response);
  }

}
