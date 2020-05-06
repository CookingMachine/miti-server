package com.miti.server.controller.controllerHTML;

import com.miti.server.check.RecipeChecker;
import com.miti.server.entity.Category;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import com.miti.server.service.CategoryService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class RecipeControllerHTML {
    private final UserService userService;
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeControllerHTML(UserService userService, RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/recipeHTML")
    public String showAllRecipes(Map<String, Object> model) {
        String message = "";
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.put("recipes", recipes);
        model.put("message", message);

        return "recipe";
    }

    @PostMapping("/recipeHTML")
    public String addRecipe(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String authorName,
                            @RequestParam String category,
                            Map<String, Object> model) {
        RecipeChecker rc = new RecipeChecker();
        String message = "";

        if (rc.recipeChecker(name, userService.getUserByUserName(authorName))) {
            recipeService.addRecipe(name, description,
                    userService.getUserByUserName(authorName),
                    categoryService.getCategoryByName(category));
        } else
            message = "Author doesnt exist or name is empty";

        List<Recipe> recipes = recipeService.getAllRecipes();
        model.put("recipes", recipes);
        model.put("message", message);

        return "recipe";
    }

}
