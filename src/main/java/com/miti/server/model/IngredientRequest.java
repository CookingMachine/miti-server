package com.miti.server.model;

import com.miti.server.model.entity.Ingredient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class IngredientRequest {
  private List<Ingredient> ingredients;
}
