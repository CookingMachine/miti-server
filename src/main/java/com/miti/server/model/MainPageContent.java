package com.miti.server.model;

import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainPageContent {

  private Recipe recipeOfTheDay;
  private List<Category> categoryList;
  private List<Recipe> lowCalories;
  private List<Recipe> fastAndDelicious;
}
