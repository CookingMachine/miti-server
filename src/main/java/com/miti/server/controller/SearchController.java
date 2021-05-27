package com.miti.server.controller;

import com.miti.data.model.Recipe;
import com.miti.server.api.request.IngredientRequest;
import com.miti.server.util.SearchFilter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/searchRecipe")
@AllArgsConstructor
public class SearchController {

  private final SearchFilter searchFilter;

  @GetMapping(path = "")
  public List<Recipe> searchRecipeByLetter(
      @RequestParam(name = "letters", defaultValue = "") String input,
      @RequestParam(name = "sort", defaultValue = "") int sorting,
      @RequestBody IngredientRequest ingredients,
      @RequestParam(name = "caloriesDown", defaultValue = "0") int caloriesDown,
      @RequestParam(name = "caloriesUp", defaultValue = "100000") int caloriesUp,
      @RequestParam(name = "timeStart", defaultValue = "0") int timeStart,
      @RequestParam(name = "timeEnd", defaultValue = "100000") int timeEnd,
      @RequestParam(name = "category", defaultValue = "NONE") String category,
      @RequestParam(name = "kitchen", defaultValue = "NONE") String kitchen) {
    if (ingredients.getIngredients() == null) {
      return null;
    }

    return searchFilter
        .searchRecipesByLetter(input, sorting, ingredients, caloriesDown, caloriesUp, timeStart,
            timeEnd,
            category, kitchen);
  }
}
