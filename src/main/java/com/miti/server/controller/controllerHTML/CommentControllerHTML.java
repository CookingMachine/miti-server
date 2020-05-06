package com.miti.server.controller.controllerHTML;

import com.miti.server.check.CommentChecker;
import com.miti.server.entity.Comment;
import com.miti.server.service.CommentService;
import com.miti.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class CommentControllerHTML {
    private final CommentService commentService;
    private final UserService userService;

    public CommentControllerHTML(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/commentHTML")
    public String showAllComments(Map<String, Object> model) {
        String message = "";
        List<Comment> comments = commentService.getAllComments();
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
                commentService.addComment(comment, userName, recipeId);
            } catch (Exception e) {
                message = "Recipe with that id doesnt exist";
            }
        } else message = "User doesn't exist";

        List<Comment> comments = commentService.getAllComments();
        model.put("message", message);
        model.put("comments", comments);

        return "comment";
    }
}
