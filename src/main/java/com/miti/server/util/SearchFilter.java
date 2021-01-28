package com.miti.server.util;

import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.Recipe;
import com.miti.server.service.ContextIngredientService;
import com.miti.server.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchFilter {
  private final RecipeService recipeService;
  private final ContextIngredientService contextIngredientService;

  public List<Recipe> searchRecipesByLetter(String input, List<Ingredient> ingredients, int timeStart, int timeEnd, String category, String kitchen) {
    List<Recipe> recipes = recipeService.getAllRecipes();
    List<String> forSorting = new ArrayList<>();
    input = input.toLowerCase();

    if (input.equals("")) {
      for (Recipe r : recipes)
        forSorting.add(r.getName());
    } else {
      input = input.trim();
      for (Recipe r : recipes) {
        String recipeName = r.getName().toLowerCase();
        if (recipeName.contains(input))
          forSorting.add(r.getName());
      }
    }
    return sortRecipes(forSorting, input, ingredients, timeStart, timeEnd, category, kitchen);
  }

  private List<Recipe> sortRecipes(List<String> recipesName, String input, List<Ingredient> ingredients, int timeStart, int timeEnd, String category, String kitchen) {
    List<String> sortedList = new ArrayList<>(recipesName);
    List<Integer> positions = new ArrayList<>();

    for (String s : recipesName) positions.add(s.indexOf(input));
    sortedList.sort(Comparator.comparing(s -> positions.get(recipesName.indexOf(s))));

    List<Recipe> result = new ArrayList<>();
    for (String s : sortedList) result.add(recipeService.getRecipeByName(s));

    return ingredientFilter(ingredients, timeStart, timeEnd, category, kitchen, result);
  }

  private List<Recipe> ingredientFilter(List<Ingredient> ingredients, int timeStart, int timeEnd, String category, String kitchen, List<Recipe> recipes) {
    List<Recipe> result = new ArrayList<>();
    List<ContextIngredient> contextIngredients = new ArrayList<>();

    for (Ingredient s : ingredients)
      contextIngredients.addAll(contextIngredientService.getContextIngredientsByIngredientId(s.getId()));
    for (Recipe r : recipes) {
      for (ContextIngredient c : contextIngredients) {
        if (c.getRecipe().getId().equals(r.getId())){
          result.add(r);
          break;
        }
      }
    }
    return timeFilter(timeStart, timeEnd, category, kitchen, result);
  }

  public List<Recipe> timeFilter(int timeStart, int timeEnd, String category, String kitchen, List<Recipe> recipes) {
    List<Recipe> result = new ArrayList<>();
    for (Recipe r : recipes) {
      if (timeStart <= r.getTime() && r.getTime() <= timeEnd)
        result.add(r);
    }
    return categoryFilter(category, kitchen, result);
  }

  public List<Recipe> categoryFilter(String category, String kitchen, List<Recipe> recipes) {
    List<Recipe> result = new ArrayList<>();
    if (category.equals("NONE"))
      result.addAll(recipes);
    else {
      category = category.toLowerCase();
      for (Recipe r : recipes) {
        if (category.equals(r.getCategory().getId().toLowerCase()))
          result.add(r);
      }
    }
    return kitchenFilter(kitchen, result);
  }

  public List<Recipe> kitchenFilter(String kitchenName, List<Recipe> recipes) {
    List<Recipe> result = new ArrayList<>();
    if (kitchenName.equals("NONE"))
      result.addAll(recipes);
    else {
      kitchenName = kitchenName.toLowerCase();
      for (Recipe r : recipes) {
        if (kitchenName.equals(r.getKitchen().name().toLowerCase()))
          result.add(r);
      }
    }
    return result;
  }
}
