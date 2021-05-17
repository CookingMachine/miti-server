package com.miti.server.api.response;

import com.miti.data.model.Category;
import com.miti.data.model.Recipe;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MainPageResponse {

  private Recipe recipeOfTheDay;

  private List<Category> categoryList;

  private List<Recipe> lowCalories;

  private List<Recipe> fastAndDelicious;
}
