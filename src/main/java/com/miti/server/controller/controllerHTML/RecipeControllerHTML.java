package com.miti.server.controller.controllerHTML;

import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.entity.User;
import com.miti.server.form.RecipeForm;
import com.miti.server.service.CategoryService;
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
public class RecipeControllerHTML {
    private RecipeService recipeService;
    private UserService userService;
    private CategoryService categoryService;

    public RecipeControllerHTML(RecipeService recipeService, UserService userService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

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
        String name = recipeForm.getName();
        String description = recipeForm.getDescription();
        User _author = userService.getUserByUserName(recipeForm.getAuthorName());
        Category _category = categoryService.getCategoryByName(recipeForm.getCategory());

        if (name != null && name.length() > 0
                && _author != null
                && _category != null) {

            recipeService.addRecipe(name, description, _author, _category);

            return "redirect:/recipeList";
        }
        model.addAttribute("errorMessage", errorMessage);

        return "recipe";
    }
}
