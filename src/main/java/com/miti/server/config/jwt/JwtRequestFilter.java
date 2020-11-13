package com.miti.server.config.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtRequestFilter extends OncePerRequestFilter {
  private final JwtUserDetailsService jwtUserDetailsService;
  private final JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    final String requestTokenHeader = request.getHeader("Authorization");

    String username = null;
    String jwtToken = null;

    if (requestTokenHeader != null) {
      logger.warn("Request Header = " + requestTokenHeader);
      if (requestTokenHeader.startsWith("Bearer ")) {
        jwtToken = requestTokenHeader.substring(7);

        try {
          username = jwtUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException iae) {
          System.out.println("Unable to het JWT Token");
        } catch (ExpiredJwtException eje) {
          System.out.println("JWT Token has expired");
        }
      } else {
        logger.warn("JWT Token does not begin with Bearer String");
      }
    } else {
      logger.warn("JWT Token is NULL!");
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
      if (jwtUtil.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    chain.doFilter(request, response);
  }
}
