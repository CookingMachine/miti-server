package com.miti.server.entity.dto;

import com.miti.server.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String username;

    private String password;

    private String email;

    private UserRole role;
}
