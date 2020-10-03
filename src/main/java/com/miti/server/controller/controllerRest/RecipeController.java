package com.miti.server.controller.controllerRest;

import com.miti.server.check.RecipeChecker;
import com.miti.server.model.dto.RecipeDTO;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.service.CategoryService;
import com.miti.server.service.RecipeService;
import com.miti.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecipeController {
    private final RecipeService recipeService;
    private final UserService userService;
    private final CategoryService categoryService;

    private RecipeChecker rc;

    @GetMapping("/getAllRecipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/getRecipeById")
    public Recipe getRecipeById(@RequestParam Long id) {
        return recipeService.getRecipeById(id);
    }

    @GetMapping("/getRecipesByCategoryId")
    public List<Recipe> getRecipesByCategoryId(@RequestParam String categoryId) {
        return recipeService.getRecipesByCategoryId(categoryId);
    }

    @GetMapping("/getRecipesByUserId")
    public List<Recipe> getRecipesByUserId(@RequestParam Long userId) {
        return recipeService.getRecipesByAuthorId(userId);
    }

    @PostMapping("/addRecipe")
    public Recipe addRecipe(@RequestBody Recipe recipe, @RequestParam String userName, @RequestParam String category) {
        Recipe newRecipe = new Recipe(new RecipeDTO(recipe.getName(),
                recipe.getDescription(),
                userService.getUserByUserName(userName),
                categoryService.getCategoryByName(category)));

            return recipeService.addRecipe(newRecipe);
    }
}
