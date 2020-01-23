package com.miti.server.repo;

import com.miti.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User getUserByUserName(String userName);

    List<User> getUsersByRole(String role);
}
