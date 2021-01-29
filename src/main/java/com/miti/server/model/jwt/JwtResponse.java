package com.miti.server.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JwtResponse {

  private final String jwtToken;
}
