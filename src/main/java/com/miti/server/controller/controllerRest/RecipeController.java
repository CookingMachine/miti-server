package com.miti.server.controller.controllerRest;

import com.miti.server.check.RecipeChecker;
import com.miti.server.entity.Category;
import com.miti.server.entity.Recipe;
import com.miti.server.service.CategoryService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeService recipeService;
    private final UserService userService;
    private final CategoryService categoryService;

    private RecipeChecker rc;

    RecipeController(RecipeService recipeService, UserService userService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/getAllRecipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/getRecipeById")
    public Recipe getRecipeById(@RequestParam Long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/getRecipesByCategory")
    public List<Recipe> getRecipesByCategory(@RequestParam String categoryName) {
        return recipeService.getRecipesByCategory(categoryName);
    }

    @PostMapping("/addRecipe")
    public Recipe addRecipe(@RequestBody Recipe recipe, @RequestParam String userName, @RequestParam String category) {
        Recipe newRecipe = new Recipe(recipe.getName(),
                recipe.getDescription(),
                userService.getUserByUserName(userName),
                categoryService.getCategoryByName(category));

            return recipeService.addRecipe(newRecipe);
    }
}