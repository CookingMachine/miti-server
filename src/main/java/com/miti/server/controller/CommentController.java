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

    @PostMapping("/addComment")
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @GetMapping("/getCommentById")
    public Comment getCommentById(@RequestParam Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @GetMapping("/getCommentsByUserId")
    public List<Comment> getCommentsByUserId(@RequestParam Long userId) {
        return commentService.getCommentsByUserId(userId);
    }

    @GetMapping("/getCommentsByRecipeId")
    public List<Comment> getCommentsByRecipeId(@RequestParam Long recipeId) {
        return commentService.getCommentsByRecipeId(recipeId);
    }

    @DeleteMapping("/deleteCommentById")
    public String deleteCommentById(@RequestParam Long commentId) {
        commentService.deleteCommentById(commentId);
        return "Done!";
    }
}
