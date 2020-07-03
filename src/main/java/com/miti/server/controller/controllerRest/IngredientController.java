package com.miti.server.controller.controllerRest;

import com.miti.server.model.entity.Ingredient;
import com.miti.server.service.IngredientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("/addIngredient")
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addIngredient(ingredient);
    }

    @GetMapping("/getIngredient")
    public Ingredient getIngredientByName(@RequestParam String name) {
        return ingredientService.getIngredientByName(name);
    }

    @GetMapping("/getAllIngredients")
    public List<Ingredient> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }
}
