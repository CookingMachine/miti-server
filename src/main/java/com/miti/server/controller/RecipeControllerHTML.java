package com.miti.server.controller;

import com.miti.server.check.RecipeChecker;
import com.miti.server.entity.Recipe;
import com.miti.server.entity.User;
import com.miti.server.repo.RecipeRepo;
import com.miti.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class RecipeControllerHTML {
    private final RecipeRepo recipeRepo;

    private final UserService userService;

    public RecipeControllerHTML(RecipeRepo recipeRepo, UserService userService) {
        this.recipeRepo = recipeRepo;
        this.userService = userService;
    }

    @GetMapping("/recipeHTML")
    public String showAllRecipes(Map<String, Object> model) {
        String message = "";
        List<Recipe> recipes = recipeRepo.findAll();
        model.put("recipes", recipes);
        model.put("message", message);

        return "recipe";
    }

    @PostMapping("/recipeHTML")
    public String addRecipe(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam String authorName,
                            Map<String, Object> model) {
        RecipeChecker rc = new RecipeChecker();
        String message = "";

        if (rc.recipeChecker(name, userService.getUserByUserName(authorName))) {
            User user = userService.getUserByUserName(authorName);
            Recipe recipe = new Recipe(name, description, user);
            recipeRepo.save(recipe);
        } else
            message = "Author doesnt exist or name is empty";

        List<Recipe> recipes = recipeRepo.findAll();
        model.put("recipes", recipes);
        model.put("message", message);

        return "recipe";
    }

}
