package com.miti.server.repo;

import com.miti.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User getUserByUserName(String userName);
}
