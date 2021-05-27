package com.miti.data.repository;

import com.miti.data.model.Recipe;
import com.miti.data.model.User;
import com.miti.data.model.Comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> getCommentsByCommentator(User commentator);

  List<Comment> getCommentsByRecipe(Recipe recipe);
}
