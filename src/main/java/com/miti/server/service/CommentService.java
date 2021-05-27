package com.miti.server.service;

import com.miti.data.model.Comment;
import java.util.List;

public interface CommentService {

  Comment addComment(Comment comment);

  void addAllComments(List<Comment> comments);

  Comment editComment(Comment newComment);

  Comment getCommentById(long commentId);

  List<Comment> getCommentsByUserId(long userId);

  List<Comment> getCommentsByRecipeId(long recipeId);

  void deleteCommentById(long commentId);
}
