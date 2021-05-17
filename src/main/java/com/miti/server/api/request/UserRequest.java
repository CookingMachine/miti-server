package com.miti.server.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {

  String username;

  String name;

  String password;

  String email;
}
