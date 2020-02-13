package com.miti.server.controller.controllerRest;

import com.miti.server.entity.IngredientContext;
import com.miti.server.service.IngredientContextService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientContextController {
    private final IngredientContextService ingredientContextService;

    public IngredientContextController(IngredientContextService ingredientContextService) {
        this.ingredientContextService = ingredientContextService;
    }

    @PostMapping("/addIngredientContext")
    public IngredientContext addIngredientContext(@RequestBody IngredientContext ingredientContext) {
        return ingredientContextService.addIngredientContext(ingredientContext);
    }

    @GetMapping("/getIngredientContextById")
    public IngredientContext getIngredientContextById(@RequestParam Long id) {
        return ingredientContextService.getIngredientContextById(id);
    }

    @GetMapping("/getIngredientContextByRecipeId")
    public List<IngredientContext> getIngredientContextByRecipe(@RequestParam Long recipeId) {
        return ingredientContextService.getIngredientContextByRecipeId(recipeId);
    }
}
