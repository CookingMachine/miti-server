package com.miti.server.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UserResponse {

  private String username;

  private String email;

  private String jwtToken;

  public UserResponse(String username, String email) {
    this.username = username;
    this.email = email;
  }
}
