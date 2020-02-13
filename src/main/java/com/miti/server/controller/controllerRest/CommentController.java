package com.miti.server.controller.controllerRest;

import com.miti.server.entity.Comment;
import com.miti.server.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/addComment")
    public Comment addComment(@RequestParam String text,
                              @RequestParam String userName,
                              @RequestParam Long recipeId) {
        return commentService.addComment(text, userName, recipeId);
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
