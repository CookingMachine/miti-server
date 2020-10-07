package com.miti.server.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtil {
  public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

  @Value("${jwt.secret}")
  private String jwt_secret;

  public String getUsernameFromToken (String token) {
    return getClaimFromToken (token, Claims::getSubject);
  }

  public Date getExpirationDateFromToken (String token) {
    return getClaimFromToken (token, Claims::getExpiration);
  }

  public <T> T getClaimFromToken (String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  public Claims getAllClaimsFromToken (String token) {
    return Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired (String token) {
    final Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  public String generateToken (UserDetails user) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken (claims, user.getUsername());
  }

  private String doGenerateToken (Map<String, Object> claims, String subject) {
    return Jwts.builder().setClaims(claims).
        setSubject(subject).
        setIssuedAt(new Date(System.currentTimeMillis())).
        setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000)).
        signWith(SignatureAlgorithm.HS512, jwt_secret).
        compact();
  }

  public Boolean validateToken (String token, UserDetails user) {
    final String username = getUsernameFromToken(token);
    return (username.equals(user.getUsername()) && !isTokenExpired(token));
  }
}
