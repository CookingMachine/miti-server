package com.miti.server.controller;

import com.miti.server.config.jwt.JwtUserDetailsService;
import com.miti.server.config.jwt.JwtUtil;
import com.miti.server.model.entity.User;
import com.miti.server.model.jwt.JwtRequest;
import com.miti.server.model.jwt.JwtResponse;
import com.miti.server.repository.UserRepository;
import com.miti.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationController {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final JwtUserDetailsService userDetailsService;

  @RequestMapping(value = "/authorization", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(authenticationRequest.getUsername());

    System.out.println(authenticationRequest.getUsername() + " ," + authenticationRequest.getPassword());

    final String token = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(new JwtResponse(token));
  }

  private void authenticate (String username, String password) throws Exception {
    try {
      User user = userService.getUserByUsername(username);
      user.setLastAuthDate(new Date());
      userRepository.save(user);
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException de) {
      throw new Exception("USER_DISABLED", de);
    } catch (BadCredentialsException bce) {
      throw new Exception("INVALID_CREDENTIALS", bce);
    }
  }
}
