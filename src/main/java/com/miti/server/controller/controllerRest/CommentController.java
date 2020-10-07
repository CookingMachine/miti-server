package com.miti.server.controller.controllerRest;

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
    public Comment addComment(@RequestParam String text,
                              @RequestParam Long userId,
                              @RequestParam Long recipeId) {
        return commentService.addComment(text, userId, recipeId);
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
