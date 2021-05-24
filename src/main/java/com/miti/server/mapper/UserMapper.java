package com.miti.server.mapper;

import com.miti.server.model.entity.User;
import com.miti.server.model.enums.Role;
import com.miti.server.model.request.UserRequest;
import com.miti.server.model.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserMapper {

  private final PasswordEncoder passwordEncoder;

  public User userRequestToUserModel(UserRequest user) {

    return user == null ? null :
        new User(user.getUsername(),
            user.getName(),
            passwordEncoder.encode(user.getPassword()),
            user.getEmail(),
            Role.USER);
  }

  public UserResponse userModelToUserResponse(User user) {
    return user == null ? null :
        new UserResponse(user.getId(), user.getUsername(), user.getName(), user.getEmail());
  }
}
