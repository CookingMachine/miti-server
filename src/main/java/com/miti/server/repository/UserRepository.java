package com.miti.server.repository;

import com.miti.server.model.entity.User;
import com.miti.server.model.enums.Role;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User getUserByUsername(String username);

  User getUserByName(String name);

  User getUserByEmail(String email);

  List<User> getUsersByStatus(Boolean status);

  List<User> getUsersByRole(Role role);

  List<User> getUsersByLastAuthDateAfter(Date date);

  List<User> getUsersByRegistrationDateAfter(Date date);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
