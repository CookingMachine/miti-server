package com.miti.server.controller.controllerHTML;

import com.miti.server.entity.Ingredient;
import com.miti.server.entity.IngredientContext;
import com.miti.server.entity.Recipe;
import com.miti.server.form.IngredientContextForm;
import com.miti.server.form.IngredientForm;
import com.miti.server.service.IngredientContextService;
import com.miti.server.service.IngredientService;
import com.miti.server.service.RecipeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IngredientContextControllerHTML {
    private IngredientContextService ingredientContextService;
    private IngredientService ingredientService;
    private RecipeService recipeService;

    public IngredientContextControllerHTML(IngredientContextService ingredientContextService,
                                           IngredientService ingredientService,
                                           RecipeService recipeService) {
        this.ingredientContextService = ingredientContextService;
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/ingredientContextList"}, method = RequestMethod.GET)
    public String ingredientContextList(Model model) {
        List<IngredientContext> ingredientContexts = ingredientContextService.getAllIngredientContexts();
        model.addAttribute("ingredientContexts", ingredientContexts);

        return "lists/ingredientContextList";
    }

    @RequestMapping(value = {"/ingredientContext"}, method = RequestMethod.GET)
    public String showAddIngredientContextPage(Model model) {
        IngredientContextForm ingredientContextForm = new IngredientContextForm();
        model.addAttribute("ingredientContextForm", ingredientContextForm);

        return "ingredientContext";
    }

    @RequestMapping(value = {"/ingredientContext"}, method = RequestMethod.POST)
    public String addIngredientContext(Model model,
                                       @ModelAttribute("ingredientContextForm")
                                               IngredientContextForm ingredientContextForm) {
        double count = ingredientContextForm.getCount();
        String flag = ingredientContextForm.getFlag();
        Ingredient _ingredient = ingredientService.getIngredientByName(ingredientContextForm.getIngredientName());
        Recipe _recipe = recipeService.getRecipeById(ingredientContextForm.getRecipeId());

        if (count > 0 && flag != null && flag.length() > 0
                && _ingredient != null && _recipe != null) {
            ingredientContextService.addIngredientContext(count, flag, _recipe, _ingredient);

            return "redirect:/ingredientContextList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "ingredientContext";

    }
}
