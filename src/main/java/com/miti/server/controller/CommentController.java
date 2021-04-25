package com.miti.server.controller;

import com.miti.server.model.entity.Comment;
import com.miti.server.api.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

  private final CommentService commentService;

  @PostMapping("")
  public Comment addComment(@RequestBody Comment comment) {
    return commentService.addComment(comment);
  }

  @PutMapping("/{id}")
  public Comment editComment(@PathVariable Long id, @RequestBody Comment comment) {
    return commentService.editComment(id, comment);
  }

  @GetMapping("/{id}")
  public Comment getCommentById(@PathVariable Long id) {
    return commentService.getCommentById(id);
  }

  @GetMapping("/getCommentsByUserId/{id}")
  public List<Comment> getCommentsByUserId(@PathVariable Long id) {
    return commentService.getCommentsByUserId(id);
  }

  @GetMapping("/getCommentsByRecipeId/{id}")
  public List<Comment> getCommentsByRecipeId(@PathVariable Long id) {
    return commentService.getCommentsByRecipeId(id);
  }

  @DeleteMapping("/{id}")
  public String deleteCommentById(@PathVariable Long id) {
    commentService.deleteCommentById(id);
    return "Done!";
  }
}
