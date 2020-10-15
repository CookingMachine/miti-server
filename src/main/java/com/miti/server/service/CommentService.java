package com.miti.server.service;

import com.miti.server.model.entity.Comment;

import java.util.List;

public interface CommentService {
  Comment addComment(Comment comment);
  void addAllComments(List<Comment> comments);

  Comment editComment(Long commentId, Comment newComment);

  Comment getCommentById(Long commentId);

  List<Comment> getCommentsByUserId(Long userId);
  List<Comment> getCommentsByRecipeId(Long recipeId);

  void deleteCommentById(Long commentId);
}
