package com.miti.server.util;

import com.miti.server.model.IngredientRequest;
import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.Rating;
import com.miti.server.model.entity.Recipe;
import com.miti.server.service.ContextIngredientService;
import com.miti.server.service.IngredientService;
import com.miti.server.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchFilter {

  private final RecipeService recipeService;
  private final IngredientService ingredientService;
  private final ContextIngredientService contextIngredientService;

  public List<Recipe> searchRecipesByLetter(String input, int sorting, IngredientRequest ingredients,
                                            int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
                                            String category, String kitchen) {

    List<Recipe> recipes = recipeService.getAllRecipes();
    List<Recipe> forSorting = new ArrayList<>();
    input = input.toLowerCase().trim();

    if (input.equals(""))
        forSorting.addAll(recipes);
    else {
      for (Recipe r : recipes) {
        String recipeName = r.getName().toLowerCase();
        if (recipeName.contains(input))
          forSorting.add(r);
      }
    }

    switch (sorting) {
      case 1:
        return sortRecipesByAlphabet(forSorting, input, ingredients, caloriesDown, caloriesUp, timeStart, timeEnd, category, kitchen);
      case 2:
        return sortRecipesByDate(forSorting, input, ingredients, caloriesDown, caloriesUp, timeStart, timeEnd, category, kitchen);
      case 3:
        return sortRecipesByRating(forSorting, input, ingredients, caloriesDown, caloriesUp, timeStart, timeEnd, category, kitchen);
      default:
        return null;
    }

  }

  private List<Recipe> sortRecipesByAlphabet(List<Recipe> recipes, String input, IngredientRequest ingredients,
                                             int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
                                             String category, String kitchen) {

    List<Recipe> result = recipes.stream().sorted(Comparator.comparing(Recipe::getName)).collect(Collectors.toList());

    return sortRecipes(getRecipeName(result),
      input, ingredients,
      caloriesDown, caloriesUp, timeStart , timeEnd,
      category, kitchen);
  }

  private List<Recipe> sortRecipesByDate(List<Recipe> recipes, String input, IngredientRequest ingredients,
                                         int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
                                         String category, String kitchen) {

    for (int i = 0; i < recipes.size(); i++) {
      Recipe min = new Recipe(recipes.get(i));
      int minId = i;
      for (int j = i+1; j < recipes.size(); j++) {
        if (recipes.get(j).getCreateDate().getTime() < min.getCreateDate().getTime()) {
          min = new Recipe(recipes.get(j));
          minId = j;
        }
      }
      Recipe temp = new Recipe(recipes.get(i));
      recipes.set(i, min);
      recipes.set(minId, temp);
    }

    return sortRecipes(getRecipeName(recipes),
      input, ingredients,
      caloriesDown, caloriesUp, timeStart, timeEnd,
      category, kitchen);
  }

  private List<Recipe> sortRecipesByRating(List<Recipe> recipes, String input, IngredientRequest ingredients,
                                         int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
                                         String category, String kitchen) {
    List<Recipe> sortedList = new ArrayList<>(recipes);
    List<Integer> avg = new ArrayList<>(getAverageRating(recipes));

    sortedList.sort(Comparator.comparing(s -> avg.get(recipes.indexOf(s))).reversed());

    return sortRecipes(getRecipeName(sortedList),
      input, ingredients,
      caloriesDown, caloriesUp, timeStart, timeEnd,
      category, kitchen);
  }

  private List<Recipe> sortRecipes(List<String> recipesName, String input, IngredientRequest ingredients,
                                   int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
                                   String category, String kitchen) {

    List<String> sortedList = new ArrayList<>(recipesName);
    List<Integer> positions = new ArrayList<>();

    for (String s : recipesName) positions.add(s.indexOf(input));
    sortedList.sort(Comparator.comparing(s -> positions.get(recipesName.indexOf(s))));

    List<Recipe> result = new ArrayList<>();
    for (String s : sortedList) result.add(recipeService.getRecipeByName(s));

    return ingredientFilter(ingredients, caloriesDown, caloriesUp, timeStart, timeEnd, category, kitchen, result);
  }

  private List<Recipe> ingredientFilter(IngredientRequest ingredients,
                                        int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
                                        String category, String kitchen, List<Recipe> recipes) {

    List<Recipe> result = new ArrayList<>();
    List<ContextIngredient> contextIngredients = new ArrayList<>();
    if (ingredients.getIngredients().size() == 0)
      ingredients.getIngredients().addAll(ingredientService.getAllIngredients());

    for (Ingredient s : ingredients.getIngredients())
      contextIngredients.addAll(contextIngredientService.getContextIngredientsByIngredientId(s.getId()));
    for (Recipe r : recipes) {
      for (ContextIngredient c : contextIngredients) {
        if (c.getRecipe().getId().equals(r.getId())){
          result.add(r);
          break;
        }
      }
    }
    return caloriesFilter(caloriesDown, caloriesUp, timeStart, timeEnd, category, kitchen, result);
  }

  private List<Recipe> caloriesFilter(int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
                                      String category, String kitchen, List<Recipe> recipes) {

    List<Recipe> result = new ArrayList<>();
    for (Recipe r : recipes) {
      if (caloriesDown <= r.getCalorie().getCalories() && r.getCalorie().getCalories() <= caloriesUp)
        result.add(r);
    }
    return timeFilter(timeStart, timeEnd, category, kitchen, result);
  }

  public List<Recipe> timeFilter(int timeStart, int timeEnd, String category,
                                 String kitchen, List<Recipe> recipes) {

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

  private List<String> getRecipeName(List<Recipe> recipes) {
    List<String> result = new ArrayList<>();

    for (Recipe r : recipes)
      result.add(r.getName());

    return result;
  }

  private List<Integer> getAverageRating(List<Recipe> recipe) {
    ArrayList<Integer> avgList = new ArrayList<>();
    int sum = 0;
    int count = 1;
    for (Recipe r : recipe) {
      if (r.getRating() != null && !r.getRating().isEmpty()) {
        for (Rating rating : r.getRating()) {
          sum += rating.getRatingValue();
          count++;
        }
        count--;
        avgList.add(sum/count);
      }
      else avgList.add(0);

      System.out.println("sum: " + sum + "\ncount: " + count + "\navg: " + sum/count);
      sum = 0;
      count = 1;

    }
    return avgList;
  }
}