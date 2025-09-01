package com.prueba.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.prueba.demo.dto.allChecksUser;
import com.prueba.demo.dto.lastCheckForUser;
import com.prueba.demo.dto.numberChecksLogs;
import com.prueba.demo.dto.userInformationDto;
import com.prueba.demo.models.user;
import com.prueba.demo.respositories.usersRespository;

@Service
public class userService implements UserDetailsService {
  @Autowired
  private usersRespository userRepository;
  @Autowired
  private PasswordEncoder passwordEncoderService;

  public List<user> getAll() {
    return userRepository.findAll();
  }

  public user getByEmail(String email) {
    return userRepository.findByEmail(email).get();
  }

  public user createUser(user newUser) {
    newUser.setPassword(passwordEncoderService.encode(newUser.getPassword()));
    return userRepository.save(newUser);
  }

  public user findById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public Page<lastCheckForUser> getUserWithLastCheck(Pageable pageable, String fullName) {
    return userRepository.findUsersWithLastCheckLog(pageable, fullName);
  }
  
  public Page<allChecksUser> getUserWithAllCheck(Pageable pageable, String fullName) {
    return userRepository.findUsersWithAllCheckLog(pageable, fullName);
  }

  public numberChecksLogs getCountUserWithCheckInAndCheckOut() {
    numberChecksLogs numberChecksLogs = new numberChecksLogs(userRepository.countUsersWithLastCheckIn(), userRepository.countUsersWithLastCheckOut());
    return numberChecksLogs;
  }

  public userInformationDto getUserInformation(){
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String email = auth.getName();
    userInformationDto user = userRepository.findLastCheckInOutByEmail(email).orElse(null);
    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    user user = getByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("Usuario no encontrado: " + email);
    }

    return org.springframework.security.core.userdetails.User.builder()
        .username(user.getEmail())
        .password(user.getPassword())
        .roles(user.getRole().name())
        .build();
  }
}
