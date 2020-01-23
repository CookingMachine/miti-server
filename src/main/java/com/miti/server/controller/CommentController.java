package com.miti.server.controller;

import com.miti.server.entity.Comment;
import com.miti.server.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getCommentsByUser")
    public List<Comment> getCommentsByUser(@RequestParam String userName) {
        return commentService.getCommentsByCommentator(userName);
    }

    @GetMapping("/getCommentsByRecipe")
    public List<Comment> getCommentsByRecipe(@RequestParam Long recipeId) {
        return commentService.getCommentsByRecipe(recipeId);
    }
}
