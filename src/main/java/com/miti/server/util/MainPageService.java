package com.miti.server.util;

import com.miti.server.api.CalorieContentService;
import com.miti.server.api.CategoryService;
import com.miti.server.api.RecipeService;
import com.miti.server.model.MainPageContent;
import com.miti.server.model.entity.Recipe;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageService {

  private final RecipeService recipeService;
  private final CategoryService categoryService;
  private final CalorieContentService calorieContentService;
  private final SearchFilter searchFilter;

  private static final Long  MAIN_PAGE_CALORIE_TOP_LIMIT = 550L;
  private static final int MAIN_PAGE_TIME_TOP_LIMIT = 1000;

  public MainPageContent mainPage() {

    return new MainPageContent(
        getRecipeOfTheDay(LocalDateTime.now()),
        categoryService.getAllCategories(),
        getRecipesWithLowCalories(),
        getFastAndDelicious());
  }

  private Recipe getRecipeOfTheDay(LocalDateTime date) {
    return getSortedRecipesByAverageRating(
        recipeService.getRecipesByCreateDateBetween(date.minusDays(3L))).get(0);
  }

  private List<Recipe> getRecipesWithLowCalories() {

    return calorieContentService.getCalorieContentsByCaloriesLessThan(MAIN_PAGE_CALORIE_TOP_LIMIT).stream().map(
        recipeService::getRecipeByCalorie).collect(
        Collectors.toList());
  }

  private List<Recipe> getFastAndDelicious() {
    return getSortedRecipesByAverageRating(recipeService.getRecipesByTimeLessThanEqual(MAIN_PAGE_TIME_TOP_LIMIT));
  }

  private List<Recipe> getSortedRecipesByAverageRating(List<Recipe> recipesBeforeSorting) {
    List<Double> averageRating = searchFilter.getAverageRating(recipesBeforeSorting);
    List<Recipe> recipesAfterSorting = new ArrayList<>(recipesBeforeSorting);

    recipesAfterSorting.sort(Comparator.comparing(s -> averageRating.get(recipesBeforeSorting.indexOf(s))));
    Collections.reverse(recipesAfterSorting);

    return recipesAfterSorting;
  }
}
