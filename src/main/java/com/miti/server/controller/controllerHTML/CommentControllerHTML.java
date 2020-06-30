package com.miti.server.controller.controllerHTML;

import com.miti.server.entity.Comment;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import com.miti.server.form.CommentForm;
import com.miti.server.service.CommentService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CommentControllerHTML {
    private CommentService commentService;
    private UserService userService;
    private RecipeService recipeService;

    public CommentControllerHTML(CommentService commentService, UserService userService, RecipeService recipeService) {
        this.commentService = commentService;
        this.userService = userService;
        this.recipeService = recipeService;
    }

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/commentList"}, method = RequestMethod.GET)
    public String commentList(Model model) {
        List<Comment> comments = commentService.getAllComments();
        model.addAttribute("comments", comments);

        return "lists/commentList";
    }

    @RequestMapping(value = {"/comment"}, method = RequestMethod.GET)
    public String showAddCommentPage(Model model) {
        CommentForm commentForm = new CommentForm();
        model.addAttribute("commentForm", commentForm);

        return "comment";
    }

    @RequestMapping(value = {"/comment"}, method = RequestMethod.POST)
    public String addComment(Model model, @ModelAttribute("commentForm") CommentForm commentForm) {
        String comment = commentForm.getCommentText();
        User _user = userService.getUserById(commentForm.getUserId());
        Recipe _recipe = recipeService.getRecipeById(commentForm.getRecipeId());

        if (_user != null && _recipe != null) {
            commentService.addComment(comment, _user.getUserName(), _recipe.getId());

            return "redirect:/commentList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "comment";
    }
}
