package com.miti.server.model.response;

import com.miti.server.model.entity.CalorieContent;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeResponse {

  String name;

  String description;

  int time;

  String kitchen;

  User author;

  Category category;

  List<ContextIngredient> contextIngredientList;

  CalorieContent calorieContent;

  String image;

}
