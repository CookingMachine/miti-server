package com.miti.server.model.dto;

import com.miti.server.enums.Measure;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.model.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientContextDTO {
    int amount;

    Measure measure;

    Ingredient ingredient;

    Recipe recipe;
}
