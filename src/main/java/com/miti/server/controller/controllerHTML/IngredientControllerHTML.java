package com.miti.server.controller.controllerHTML;

import com.miti.server.check.IngredientChecker;
import com.miti.server.entity.Ingredient;
import com.miti.server.service.IngredientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class IngredientControllerHTML {

    private final IngredientService ingredientService;

    public IngredientControllerHTML(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredientHTML")
    public String showAllIngredients(Map<String, Object> model) {
        String message = "";
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        model.put("message", message);
        model.put("ingredients", ingredients);

        return "ingredient";
    }

    @PostMapping("/ingredientHTML")
    public String addIngredient(@RequestParam String name,
                                Map<String, Object> model) {
        String message = "";

        if (IngredientChecker.check(name) && ingredientService.getIngredientByName(name) == null) {
            ingredientService.addIngredient(name);
        } else
            message = "Ingredient already exists";

        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        model.put("message", message);
        model.put("ingredients", ingredients);

        return "ingredient";
    }
}
