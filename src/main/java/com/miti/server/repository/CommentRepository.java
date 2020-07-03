package com.miti.server.repository;

import com.miti.server.entity.Comment;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentsByCommentator(User user);

    List<Comment> getCommentsByRecipe(Recipe recipe);
}