package com.prueba.demo.dto;

import com.prueba.demo.models.user;

public class userTokeDto {
  private String token;
  private user user;

  public userTokeDto(user user, String token) {
    this.user = user;
    this.token = token;
  }

  public userTokeDto() {
    this.user = null;
    this.token = "";
  }

  public user getUser() {
    return user;
  }
  public void setUser(user user) {
    this.user = user;
  }
  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
