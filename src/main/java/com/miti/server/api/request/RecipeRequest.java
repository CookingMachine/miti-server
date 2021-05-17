package com.miti.server.api.request;

import com.miti.data.model.User;
import com.miti.data.model.CalorieContent;
import com.miti.data.model.Category;
import com.miti.data.model.ContextIngredient;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
