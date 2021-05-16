package com.miti.server.controller;

import com.miti.server.api.CommentService;
import com.miti.server.model.entity.Comment;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/comment")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

  private final CommentService commentService;

  @PostMapping("")
  public Comment addComment(@RequestBody Comment comment) {
    return commentService.addComment(comment);
  }

  @PatchMapping("/{id}")
  public Comment editComment(@RequestBody Comment comment) {
    return commentService.editComment(comment);
  }

  @GetMapping("/{id}")
  public Comment getCommentById(@PathVariable Long id) {
    return commentService.getCommentById(id);
  }

  @GetMapping("/getByUserId/{id}")
  public List<Comment> getCommentsByUserId(@PathVariable Long id) {
    return commentService.getCommentsByUserId(id);
  }

  @GetMapping("/getByRecipeId/{id}")
  public List<Comment> getCommentsByRecipeId(@PathVariable Long id) {
    return commentService.getCommentsByRecipeId(id);
  }

  @DeleteMapping("/{id}")
  public String deleteCommentById(@PathVariable Long id) {
    commentService.deleteCommentById(id);

    return "Successfully removed COMMENT with id [" + id + "]";
  }
}
