package com.miti.server.controller.controllerHTML;

import com.miti.server.model.entity.Comment;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.model.dto.CommentDTO;
import com.miti.server.service.CommentService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentControllerHTML {
    private final CommentService commentService;
    private final UserService userService;
    private final RecipeService recipeService;

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
        CommentDTO commentDTO = new CommentDTO();
        model.addAttribute("commentDTO", commentDTO);

        return "comment";
    }

    @RequestMapping(value = {"/comment"}, method = RequestMethod.POST)
    public String addComment(Model model, @ModelAttribute("commentDTO") CommentDTO commentDTO) {
        String comment = commentDTO.getCommentText();
        User _user = userService.getUserById(commentDTO.getUserId());
        Recipe _recipe = recipeService.getRecipeById(commentDTO.getRecipeId());

        if (_user != null && _recipe != null) {
            commentService.addComment(comment, _user.getId(), _recipe.getId());

            return "redirect:/commentList";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "comment";
    }
}
