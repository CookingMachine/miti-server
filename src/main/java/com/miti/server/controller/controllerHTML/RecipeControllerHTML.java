package com.miti.server.controller.controllerHTML;

import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.model.dto.RecipeDTO;
import com.miti.server.model.form.RecipeForm;
import com.miti.server.service.CategoryService;
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
public class RecipeControllerHTML {
    private final RecipeService recipeService;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/recipeList"}, method = RequestMethod.GET)
    public String recipeList(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);

        return "lists/recipeList";
    }

    @RequestMapping(value = {"/recipe"}, method = RequestMethod.GET)
    public String showAddRecipePage(Model model) {
        RecipeForm recipeForm = new RecipeForm();
        model.addAttribute("recipeForm", recipeForm);

        return "recipe";
    }

    @RequestMapping(value = {"/recipe"}, method = RequestMethod.POST)
    public String save(Model model, @ModelAttribute("recipeForm") RecipeForm recipeForm) {

        if (recipeService.checkFieldsExist(recipeForm.getAuthorId(), recipeForm.getCategoryId())) {

            recipeService.addRecipeDTO(recipeForm);

            return "redirect:/recipeList";
        }

        model.addAttribute("errorMessage", errorMessage);

        return "recipe";
    }
}
