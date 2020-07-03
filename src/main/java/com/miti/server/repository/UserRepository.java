package com.miti.server.repository;

import com.miti.server.entity.User;
import com.miti.server.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String userName);

    List<User> getUsersByRole(UserRole role);

    boolean existsByUsername(String name);
}
