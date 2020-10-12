package com.miti.server.model.dto;

import com.miti.server.enums.Measure;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientContextDTO {
  private Long amount;
  private Measure measure;
  private String ingredientId;
  private Long recipeId;
}
