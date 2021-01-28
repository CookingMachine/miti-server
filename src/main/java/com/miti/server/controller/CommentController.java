package com.miti.server.controller;

import com.miti.server.model.entity.Comment;
import com.miti.server.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {
  private final CommentService commentService;

  @PostMapping("/comment/addComment")
  public Comment addComment(@RequestBody Comment comment) {
    return commentService.addComment(comment);
  }

  @PutMapping("/comment/editComment")
  public Comment editComment(@RequestParam(name = "commentId") Long commentId, @RequestBody Comment comment) {
    return commentService.editComment(commentId, comment);
  }

  @GetMapping("/comment/getCommentById")
  public Comment getCommentById(@RequestParam(name = "commentId") Long commentId) {
    return commentService.getCommentById(commentId);
  }

  @GetMapping("/comment/getCommentsByUserId")
  public List<Comment> getCommentsByUserId(@RequestParam(name = "userId") Long userId) {
    return commentService.getCommentsByUserId(userId);
  }

  @GetMapping("/comment/getCommentsByRecipeId")
  public List<Comment> getCommentsByRecipeId(@RequestParam(name = "recipeId") Long recipeId) {
    return commentService.getCommentsByRecipeId(recipeId);
  }

  @DeleteMapping("/comment/deleteCommentById")
  public String deleteCommentById(@RequestParam(name = "commentId") Long commentId) {
    commentService.deleteCommentById(commentId);
    return "Done!";
  }
}
