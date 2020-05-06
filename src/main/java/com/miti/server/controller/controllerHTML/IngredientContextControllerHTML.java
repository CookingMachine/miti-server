package com.miti.server.controller.controllerHTML;

import com.miti.server.entity.IngredientContext;
import com.miti.server.service.IngredientContextService;
import com.miti.server.service.IngredientService;
import com.miti.server.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class IngredientContextControllerHTML {
    private final IngredientContextService ingredientContextService;
    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private String message = "";

    public IngredientContextControllerHTML(IngredientContextService ingredientContextService,
                                           RecipeService recipeService,
                                           IngredientService ingredientService) {
        this.ingredientContextService = ingredientContextService;
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredientContextHTML")
    public String getAllIngredientContext(Map<String, Object> model) {
        List<IngredientContext> ingredientContexts = ingredientContextService.getAllIngredientContexts();
        model.put("ingredientContexts", ingredientContexts);
        model.put("message", message);

        return "ingredientContext";
    }

    @PostMapping("/ingredientContextHTML")
    public String addIngredientContext(@RequestParam double count,
                                       @RequestParam String flag,
                                       @RequestParam Long recipeId,
                                       @RequestParam String ingredientName,
                                       Map<String, Object> model) {

        ingredientContextService.addIngredientContext(count, flag, recipeService.getRecipeById(recipeId), ingredientService.getIngredientByName(ingredientName));

        List<IngredientContext> ingredientContexts = ingredientContextService.getAllIngredientContexts();
        model.put("ingredientContexts", ingredientContexts);
        model.put("message", message);

        return "ingredientContext";

    }
}
