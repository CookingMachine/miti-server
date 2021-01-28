package com.miti.server.controller;

import com.miti.server.model.entity.Recipe;
import com.miti.server.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchController {
  private final RecipeService recipeService;

  @RequestMapping(value = "/searchRecipe", method = RequestMethod.GET)
  public List<Recipe> searchRecipeByLetter(@RequestParam(name = "letters") String letters) {
    return recipeService.searchRecipesByLetter(letters);
  }

}
