package com.miti.server.service;

import com.miti.server.entity.User;

public interface UserService {
    User getUserByUserName(String userName);
}
