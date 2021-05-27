package com.miti.server.controller;

import com.miti.server.api.response.UserResponse;
import com.miti.server.service.UserService;
import com.miti.server.config.jwt.JwtUserDetailsService;
import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.jwt.JwtRequest;
import com.miti.server.util.Authenticate;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/authorization")
@AllArgsConstructor
public class JwtAuthenticationController {

  private final Authenticate authenticate;
  private final UserService userService;
  private final JwtUserDetailsService jwtUserService;
  private final JwtUtil jwtUtil;

  @PostMapping
  public UserResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
      throws Exception {

    return userService.getUserByToken(authenticate.generateJwtToken(authenticationRequest));
  }

  @GetMapping(value = "/validate")
  public UserResponse validateJWTToken(@RequestHeader(name = "Authorization") String jwtToken) {
    try {
      jwtUtil.validateToken(jwtToken.substring(7),
          jwtUserService.loadUserByUsername(jwtUtil.getUsernameFromToken(jwtToken.substring(7))));
      return userService.getUserByToken(jwtToken.substring(7));
    } catch (ExpiredJwtException eje) {
      return null;
    }
  }
}
