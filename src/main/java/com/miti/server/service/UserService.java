package com.miti.server.service;

import com.miti.server.entity.User;

public interface UserService {
    void addUser(User user);

    User getUserByUserName(String userName);
}
