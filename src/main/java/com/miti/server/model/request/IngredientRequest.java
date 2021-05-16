package com.miti.server.model.request;

import com.miti.server.model.entity.Ingredient;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IngredientRequest {

  private List<Ingredient> ingredients;
}
