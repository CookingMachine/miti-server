package com.miti.server.controller.controllerHTML;

import com.miti.server.enums.Measure;
import com.miti.server.model.dto.IngredientContextDTO;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.IngredientContext;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.form.IngredientContextForm;
import com.miti.server.service.IngredientContextService;
import com.miti.server.service.IngredientService;
import com.miti.server.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IngredientContextControllerHTML {
    private final IngredientContextService ingredientContextService;
    private final IngredientService ingredientService;
    private final RecipeService recipeService;

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

        Measure[] measures = Measure.values();
        model.addAttribute("measures", measures);

        return "ingredientContext";
    }

    @RequestMapping(value = {"/ingredientContext"}, method = RequestMethod.POST)
    public String addIngredientContext(Model model,
                                       @ModelAttribute("ingredientContext")IngredientContextForm form) {
        if (ingredientContextService.checkFieldsExist(form.getRecipeId(), form.getIngredientId())) {

            ingredientContextService.addIngredientContextDTO(form);

            return "redirect:/ingredientContextList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "ingredientContext";

    }
}
