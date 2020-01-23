package com.miti.server.controller;

import com.miti.server.check.RecipeChecker;
import com.miti.server.entity.Recipe;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecipeController {
    private final RecipeService recipeService;
    private final UserService userService;

    private RecipeChecker rc;

    RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/getAllRecipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }


    @PostMapping("/addRecipe")
    public Recipe addRecipe(@RequestBody Recipe recipe, @RequestParam String userName) {
        Recipe newRecipe = new Recipe(recipe.getName(),
                recipe.getDescription(),
                userService.getUserByUserName(userName));

            return recipeService.addRecipe(newRecipe);
    }
}
