package com.miti.server.controller;

import com.miti.server.api.UserService;
import com.miti.server.config.jwt.JwtUserDetailsService;
import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.model.jwt.JwtRequest;
import com.miti.server.model.response.UserResponse;
import com.miti.server.util.Authenticate;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/authorization")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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
