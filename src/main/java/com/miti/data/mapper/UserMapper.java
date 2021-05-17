package com.miti.data.mapper;

import com.miti.data.model.User;
import com.miti.server.api.request.UserRequest;
import com.miti.server.api.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mappings({
      @Mapping(target = "role", constant = "USER"),
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "status", ignore = true),
      @Mapping(target = "registrationDate", ignore = true),
      @Mapping(target = "lastAuthDate", ignore = true),
      @Mapping(target = "recipeList", ignore = true),
      @Mapping(target = "commentList", ignore = true),
      @Mapping(target = "favouriteList", ignore = true),
      @Mapping(target = "rating", ignore = true)
  })
  User userRequestToUserModel(UserRequest user);

  @Mapping(target = "jwtToken", ignore = true)
  UserResponse userModelToUserResponse(User user);
}
