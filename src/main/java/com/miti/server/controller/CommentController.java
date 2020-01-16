package com.miti.server.controller;

import com.miti.server.check.CommentChecker;
import com.miti.server.entity.Comment;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import com.miti.server.repo.CommentRepo;
import com.miti.server.repo.RecipeRepo;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class CommentController {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/comment")
    public String showAllComments(Map<String, Object> model) {
        String message = "";
        List<Comment> comments = commentRepo.findAll();
        model.put("message", message);
        model.put("comments", comments);

        return "comment";
    }

    @PostMapping("/comment")
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
