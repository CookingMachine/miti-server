package com.miti.server.controller;

import com.miti.server.model.jwt.JwtRequest;
import com.miti.server.util.Authenticate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path = "api/v1/authorization")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationController {

  private final Authenticate authenticate;

  @PostMapping
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
      throws Exception {

    return ResponseEntity.ok(authenticate.generateJwtToken(authenticationRequest));
  }
}
