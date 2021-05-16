package com.miti.server.config.jwt;

import com.miti.server.model.entity.User;
import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {

  private String login;
  private String password;
  private Collection<? extends GrantedAuthority> grantedAuthorities;

  public static JwtUserDetails fromUserEntityToCustomUserDetails(User user) {
    JwtUserDetails jwtUser = new JwtUserDetails();
    jwtUser.login = user.getUsername();
    jwtUser.password = user.getPassword();
    jwtUser.grantedAuthorities = Collections
        .singletonList(new SimpleGrantedAuthority(user.getRole().name()));

    return jwtUser;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return grantedAuthorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return login;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
