package com.miti.server.util;

import com.miti.server.service.UserService;
import com.miti.server.config.jwt.JwtUserDetailsService;
import com.miti.server.config.jwt.JwtUtil;
import com.miti.data.model.User;
import com.miti.server.jwt.JwtRequest;
import com.miti.data.repository.UserRepository;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Authenticate {

  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final UserRepository userRepository;
  private final JwtUtil jwtUtil;
  private final JwtUserDetailsService userDetailsService;

  public String generateJwtToken(JwtRequest authenticationRequest) throws Exception {
      authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
      final UserDetails userDetails = userDetailsService
          .loadUserByUsername(authenticationRequest.getUsername());

      return jwtUtil.generateToken(userDetails);
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      User user = userService.getUserByUsername(username);
      user.setLastAuthDate(new Date());
      userRepository.save(user);
      authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException de) {
      throw new Exception("USER_DISABLED", de);
    } catch (BadCredentialsException bce) {
      throw new Exception("INVALID_CREDENTIALS", bce);
    }
  }
}
