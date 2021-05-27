package com.miti.server.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
