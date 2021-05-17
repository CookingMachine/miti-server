package com.miti.server.service.impl;

import com.miti.data.model.Comment;
import com.miti.data.repository.CommentRepository;
import com.miti.server.service.CommentService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final UserService userService;
  private final RecipeService recipeService;

  @Override
  public Comment addComment(Comment comment) {
    return commentRepository.save(new Comment(
        comment.getComment(),
        userService.getUserById(comment.getCommentator().getId()),
        recipeService.getRecipeById(comment.getRecipe().getId())
    ));
  }

  @Override
  public void addAllComments(List<Comment> comments) {
    commentRepository.saveAll(comments);
  }

  @Override
  public Comment editComment(Comment newComment) {
    return commentRepository.findById(newComment.getId()).map(comment -> {
      comment.setComment(newComment.getComment());
      comment.setEditDate(new Date());
      return commentRepository.save(comment);
    }).orElseThrow(()
        -> new RuntimeException("Comment with id: " + newComment.getId() + " doesn't exist!"));
  }

  @Override
  public Comment getCommentById(long commentId) {
    return commentRepository.findById(commentId).orElseThrow(()
        -> new RuntimeException("Comment with id: " + commentId + " doesn't exist!"));
  }

  @Override
  public List<Comment> getCommentsByUserId(long userId) {
      List<Comment> comments = commentRepository
          .getCommentsByCommentator(userService.getUserById(userId));
      if (comments != null) {
        return comments;
      }
      throw new RuntimeException("Comments with userId: " + userId + " doesn't exist!");
  }

  @Override
  public List<Comment> getCommentsByRecipeId(long recipeId) {
      List<Comment> comments = commentRepository
          .getCommentsByRecipe(recipeService.getRecipeById(recipeId));
      if (comments != null) {
        return comments;
      }
      throw new RuntimeException("Comments with recipeId: " + recipeId + " doesn't exist!");
    }

  @Override
  public void deleteCommentById(long commentId) {
    commentRepository.deleteById(commentId);
  }
}
