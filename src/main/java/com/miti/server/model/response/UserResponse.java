package com.miti.server.model.response;

import lombok.Data;

@Data
public class UserResponse {

  private Long id;

  private String username;

  private String name;

  private String email;

  private String jwtToken;

  public UserResponse(Long id, String username, String name, String email) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.email = email;
  }
}
