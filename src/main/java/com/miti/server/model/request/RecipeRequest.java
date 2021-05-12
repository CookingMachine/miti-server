package com.miti.server.model.request;

import com.miti.server.model.entity.CalorieContent;
import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.ContextIngredient;
import com.miti.server.model.entity.User;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipeRequest {

  String name;

  String description;

  int time;

  String kitchen;

  User author;

  Category category;

  ArrayList<ContextIngredient> contextIngredientList;

  CalorieContent calorieContent;

  String image;
}
