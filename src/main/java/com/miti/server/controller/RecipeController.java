package com.miti.server.controller;

import com.miti.server.model.entity.Recipe;
import com.miti.server.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeController {
    private final RecipeService recipeService;
    @PostMapping("/addRecipe")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.addRecipe(recipe);
    }

    @GetMapping("/getRecipeById")
    public Recipe getRecipeById(@RequestParam Long recipeId) {
        return recipeService.getRecipeById(recipeId);
    }

    @GetMapping("/getRecipeByName")
    public Recipe getRecipeByName(@RequestParam String name) {
        return recipeService.getRecipeByName(name);
    }

    @GetMapping("/getAllRecipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/getRecipesByAuthorId")
    public List<Recipe> getRecipesByAuthorId(@RequestParam Long authorId) {
        return recipeService.getRecipesByAuthorId(authorId);
    }

    @GetMapping("/getRecipesByCategoryId")
    public List<Recipe> getRecipesByCategoryId(@RequestParam String categoryId) {
        return recipeService.getRecipesByCategoryId(categoryId);
    }

    @DeleteMapping("/deleteRecipeById")
    public String deleteRecipeById(@RequestParam Long recipeId) {
        recipeService.deleteRecipeById(recipeId);
        return "Done!";
    }

}
