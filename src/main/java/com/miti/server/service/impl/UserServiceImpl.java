package com.miti.server.service.impl;

import com.miti.server.entity.User;
import com.miti.server.repo.UserRepo;
import com.miti.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public User getUserByUserName(String userName) {
        return userRepo.getUserByUserName(userName);
    }
}
