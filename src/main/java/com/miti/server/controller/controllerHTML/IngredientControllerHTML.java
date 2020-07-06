package com.miti.server.controller.controllerHTML;

import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.dto.IngredientDTO;
import com.miti.server.service.IngredientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class IngredientControllerHTML {
    private IngredientService ingredientService;

    public IngredientControllerHTML(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = {"/ingredientList"}, method = RequestMethod.GET)
    public String ingredientList(Model model) {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        model.addAttribute("ingredients", ingredients);

        return "lists/ingredientList";
    }

    @RequestMapping(value = {"/ingredient"}, method = RequestMethod.GET)
    public String showAddIngredientPage(Model model) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        model.addAttribute("ingredientDTO", ingredientDTO);

        return "ingredient";
    }

    @RequestMapping(value = {"/ingredient"}, method = RequestMethod.POST)
    public String addIngredient(Model model, @ModelAttribute("ingredients") IngredientDTO ingredientDTO) {
        String id = ingredientDTO.getId();
        String name = ingredientDTO.getName();

        if (name != null && name.length() > 0) {
            ingredientService.addIngredient(new IngredientDTO(id, name));

            return "redirect:/ingredientList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "ingredient";
    }
}
