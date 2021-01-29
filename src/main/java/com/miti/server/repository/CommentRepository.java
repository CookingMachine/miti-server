package com.miti.server.repository;

import com.miti.server.model.entity.Comment;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> getCommentsByCommentator(User commentator);
  List<Comment> getCommentsByRecipe(Recipe recipe);
}
