package com.miti.server.repository;

import com.miti.server.model.entity.User;
import com.miti.server.model.enums.Role;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User getUserByUsername(String username);

  User getUserByName(String name);

  User getUserByEmail(String email);

  List<User> getUsersByStatus(Boolean status);

  List<User> getUsersByRole(Role role);

  List<User> getUsersByLastAuthDateAfter(Date date);

  List<User> getUsersByRegistrationDateAfter(Date date);

  @Modifying
  @Query(value = "INSERT INTO recipe_favourite_users VALUES (:userId, :recipeId);", nativeQuery = true)
  @Transactional
  void addFavouriteRecipe(@Param("userId") Long userId, @Param("recipeId") Long recipeId);

  @Modifying
  @Query(value = "DELETE FROM recipe_favourite_users f WHERE f.favourite_list_id = :userId AND f.favourite_users_id = :recipeId", nativeQuery = true)
  @Transactional
  void deleteFavouriteRecipe(@Param("userId") Long userId, @Param("recipeId") Long recipeId);

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);
}
