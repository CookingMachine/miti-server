package com.miti.server.form;

import com.miti.server.enums.UserRole;
import lombok.Data;

@Data
public class UserForm {

    private String userName;

    private String password;

    private UserRole role;

    private String email;
}
