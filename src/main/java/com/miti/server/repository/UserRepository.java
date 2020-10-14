package com.miti.server.repository;

import com.miti.server.enums.Role;
import com.miti.server.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User getUserByUsername(String username);
  User getUserByEmail(String email);

  List<User> getUsersByStatus(Boolean status);
  List<User> getUsersByRole(Role role);
  List<User> getUsersByLastAuthDateAfter(Date date);
  List<User> getUsersByRegistrationDateAfter(Date date);

  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
}
