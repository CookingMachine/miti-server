package com.miti.server.util;

import com.miti.server.config.jwt.JwtUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;

public class Check {

  public static boolean param(String parameter) {
    return !parameter.isEmpty();
  }

  public static boolean param(Boolean parameter) {
    return parameter != null;
  }

  public static boolean param(Long parameter) {
    return parameter != null;
  }

  public static boolean role(HttpServletRequest req,
                             UserDetailsService userDetailsService,
                             JwtUtil util,
                             String role) {
    String token = req.getHeader("Authorization").substring(7);
    UserDetails details = userDetailsService.loadUserByUsername(util.getUsernameFromToken(token));
    return details != null && details.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role));
  }
}
