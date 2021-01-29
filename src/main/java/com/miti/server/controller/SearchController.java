package com.miti.server.controller;

import com.miti.server.model.entity.Recipe;
import com.miti.server.model.IngredientRequest;
import com.miti.server.util.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchController {

  private final SearchFilter searchFilter;

  @RequestMapping(value = "/searchRecipe", method = RequestMethod.GET)
  public List<Recipe> searchRecipeByLetter(@RequestParam(name = "letters", defaultValue = "") String input,
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
    return searchFilter.searchRecipesByLetter(input, ingredients, caloriesDown, caloriesUp, timeStart, timeEnd, category, kitchen);
  }
}
