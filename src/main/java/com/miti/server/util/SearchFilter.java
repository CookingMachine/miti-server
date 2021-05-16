package com.miti.server.util;

import com.miti.server.api.ContextIngredientService;
import com.miti.server.api.IngredientService;
import com.miti.server.api.RatingService;
import com.miti.server.api.RecipeService;
import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.Rating;
import com.miti.server.model.entity.Recipe;
import com.miti.server.model.enums.Kitchen;
import com.miti.server.model.request.IngredientRequest;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchFilter {

  private final RecipeService recipeService;
  private final IngredientService ingredientService;
  private final ContextIngredientService contextIngredientService;
  private final RatingService ratingService;

  public List<Recipe> searchRecipesByLetter(String input, int sorting,
      IngredientRequest ingredients,
      int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
      String category, String kitchen) {

    List<Recipe> recipes = recipeService.getAllRecipes();
    List<Recipe> forSorting = new ArrayList<>();
    input = input.toLowerCase().trim();

    if (input.equals("")) {
      forSorting.addAll(recipes);
    } else {
      for (Recipe r : recipes) {
        String recipeName = r.getName().toLowerCase();
        if (recipeName.contains(input)) {
          forSorting.add(r);
        }
      }
    }

    switch (sorting) {
      case 1:
        return sortRecipesByAlphabet(forSorting, input, ingredients, caloriesDown, caloriesUp,
            timeStart, timeEnd, category, kitchen);
      case 2:
        return sortRecipesByDate(forSorting, input, ingredients, caloriesDown, caloriesUp,
            timeStart, timeEnd, category, kitchen);
      case 3:
        return sortRecipesByRating(forSorting, input, ingredients, caloriesDown, caloriesUp,
            timeStart, timeEnd, category, kitchen);
      default:
        return sortRecipes(forSorting, input, ingredients, caloriesDown, caloriesUp, timeStart,
            timeEnd, category, kitchen);
    }
  }

  private List<Recipe> sortRecipesByAlphabet(List<Recipe> recipes, String input,
      IngredientRequest ingredients,
      int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
      String category, String kitchen) {

    List<Recipe> result = recipes.stream().sorted(Comparator.comparing(Recipe::getName))
        .collect(Collectors.toList());

    return sortRecipes(result,
        input, ingredients,
        caloriesDown, caloriesUp, timeStart, timeEnd,
        category, kitchen);
  }

  private List<Recipe> sortRecipesByDate(List<Recipe> recipes, String input,
      IngredientRequest ingredients,
      int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
      String category, String kitchen) {

    for (int i = 0; i < recipes.size(); i++) {
      Recipe min = recipeService.getRecipeById(recipes.get(i).getId());
      int minId = i;
      for (int j = i + 1; j < recipes.size(); j++) {
        if (recipes.get(j).getCreateDate().getTime() < min.getCreateDate().getTime()) {
          min = recipeService.getRecipeById(recipes.get(j).getId());
          minId = j;
        }
      }
      Long temp = recipes.get(i).getId();
      recipes.set(i, min);
      recipes.set(minId, recipeService.getRecipeById(temp));
    }

    return sortRecipes(recipes,
        input, ingredients,
        caloriesDown, caloriesUp, timeStart, timeEnd,
        category, kitchen);
  }

  private List<Recipe> sortRecipesByRating(List<Recipe> recipes, String input,
      IngredientRequest ingredients,
      int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
      String category, String kitchen) {
    List<Recipe> sortedList = new ArrayList<>(recipes);
    List<Double> avg = new ArrayList<>(getAverageRating(recipes));

    sortedList.sort(Comparator.comparing(s -> avg.get(recipes.indexOf(s))).reversed());

    return sortRecipes(sortedList,
        input, ingredients,
        caloriesDown, caloriesUp, timeStart, timeEnd,
        category, kitchen);
  }

  private List<Recipe> sortRecipes(List<Recipe> recipes, String input,
      IngredientRequest ingredients,
      int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
      String category, String kitchen) {

    List<String> recipesName = new ArrayList<>(getRecipeName(recipes));
    List<String> sortedList = new ArrayList<>(recipesName);
    List<Integer> positions = new ArrayList<>();

    for (String s : recipesName) {
      positions.add(s.indexOf(input));
    }
    sortedList.sort(Comparator.comparing(s -> positions.get(recipesName.indexOf(s))));

    List<Recipe> result = new ArrayList<>();
    for (String s : sortedList) {
      result.addAll(recipeService.getRecipeByName(s));
    }

    return ingredientFilter(ingredients, caloriesDown, caloriesUp, timeStart, timeEnd, category,
        kitchen, result);
  }

  private List<Recipe> ingredientFilter(IngredientRequest ingredients,
      int caloriesDown, int caloriesUp, int timeStart, int timeEnd,
      String category, String kitchen, List<Recipe> recipes) {

    List<Recipe> result = new ArrayList<>();
    List<ContextIngredient> contextIngredients = new ArrayList<>();
    if (ingredients.getIngredients().size() == 0) {
      ingredients.getIngredients().addAll(ingredientService.getAllIngredients());
    }

    for (Ingredient s : ingredients.getIngredients()) {
      contextIngredients
          .addAll(contextIngredientService.getContextIngredientsByIngredientId(s.getId()));
    }
    for (Recipe r : recipes) {
      for (ContextIngredient c : contextIngredients) {
        if (c.getRecipe().getId().equals(r.getId())) {
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
      if (caloriesDown <= r.getCalorie().getCalories()
          && r.getCalorie().getCalories() <= caloriesUp) {
        result.add(r);
      }
    }
    return timeFilter(timeStart, timeEnd, category, kitchen, result);
  }

  private List<Recipe> timeFilter(int timeStart, int timeEnd, String category,
      String kitchen, List<Recipe> recipes) {

    List<Recipe> result = new ArrayList<>();
    for (Recipe r : recipes) {
      if (timeStart <= r.getTime() && r.getTime() <= timeEnd) {
        result.add(r);
      }
    }
    return categoryFilter(category, kitchen, result);
  }

  private List<Recipe> categoryFilter(String category, String kitchen, List<Recipe> recipes) {
    List<Recipe> result = new ArrayList<>();
    if (category.equals("NONE")) {
      result.addAll(recipes);
    } else {
      category = category.toLowerCase();
      for (Recipe r : recipes) {
        if (category.equals(r.getCategory().getId().toLowerCase())) {
          result.add(r);
        }
      }
    }
    return kitchenFilter(kitchen, result);
  }

  private List<Recipe> kitchenFilter(String kitchenName, List<Recipe> recipes) {
    List<Recipe> result = new ArrayList<>();
    if (kitchenName.equals("NONE")) {
      result.addAll(recipes);
    } else {
      kitchenName = kitchenName.toLowerCase();
      for (Recipe r : recipes) {
        if (kitchenName.equals(r.getKitchen().name().toLowerCase())) {
          result.add(r);
        }
      }
    }
    return result;
  }

  private List<String> getRecipeName(List<Recipe> recipes) {
    List<String> result = new ArrayList<>();

    for (Recipe r : recipes) {
      result.add(r.getName());
    }

    return result;
  }

  protected List<Double> getAverageRating(List<Recipe> recipe) {
    ArrayList<Double> avgList = new ArrayList<>();
    double sum = 0;
    int count = 1;

    DecimalFormat df = new DecimalFormat("#.#");
    df.setRoundingMode(RoundingMode.CEILING);

    for (Recipe r : recipe) {
      List<Rating> ratings = ratingService.getRatingsByRecipeId(r.getId());
      if (ratings != null) {
        for (Rating rating : ratings) {
          sum += rating.getRatingValue();
          count++;
        }
        count--;
        double avg = (double) Math.round(sum / count * 10d) / 10d;
        avgList.add(avg);
      } else {
        avgList.add(0d);
      }

      sum = 0;
      count = 1;

    }
    return avgList;
  }

  public List<String> getAllKitchen() {
    return Arrays.stream(Kitchen.values()).map(Kitchen::name).collect(Collectors.toList());
  }
}
