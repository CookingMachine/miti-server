package com.miti.server.api.request;

import com.miti.data.model.Ingredient;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientRequest {

  private List<Ingredient> ingredients;
}
