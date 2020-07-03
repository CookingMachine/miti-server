package com.miti.server.model.form;

import lombok.Data;

@Data
public class UserForm {

    private String userName;

    private String password;

    private String role;

    private String email;
}
