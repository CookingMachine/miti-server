package com.miti.server.controller;

import com.miti.server.check.CommentChecker;
import com.miti.server.entity.Comment;
import com.miti.server.entity.Recipe;
import com.miti.server.repo.CommentRepo;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class CommentControllerHTML {
    private final CommentRepo commentRepo;
    private final UserService userService;
    private final RecipeService recipeService;

    public CommentControllerHTML(CommentRepo commentRepo, UserService userService, RecipeService recipeService) {
        this.commentRepo = commentRepo;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @GetMapping("/commentHTML")
    public String showAllComments(Map<String, Object> model) {
        String message = "";
        List<Comment> comments = commentRepo.findAll();
        model.put("message", message);
        model.put("comments", comments);

        return "comment";
    }

    @PostMapping("/commentHTML")
    public String addComment(@RequestParam String userName,
                             @RequestParam String comment,
                             @RequestParam Long recipeId,
                             Map<String, Object> model) {
        CommentChecker cc = new CommentChecker();
        String message = "";

        if (cc.commentChecker(comment,
                userService.getUserByUserName(userName))) {
            try {
                Recipe recipe = recipeService.getRecipeById(recipeId);
                Comment newComment = new Comment(comment,
                        userService.getUserByUserName(userName),
                        recipe);

                commentRepo.save(newComment);
            } catch (Exception e) {
                message = "Recipe with that id doesnt exist";
            }
        } else message = "User doesnt exist";

        List<Comment> comments = commentRepo.findAll();
        model.put("message", message);
        model.put("comments", comments);

        return "comment";
    }
}
