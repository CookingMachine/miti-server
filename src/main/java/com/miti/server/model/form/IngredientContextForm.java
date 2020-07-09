package com.miti.server.model.form;

import com.miti.server.enums.Measure;
import lombok.Data;

@Data
public class IngredientContextForm {
    private int amount;

    private Measure measure;

    private String ingredientId;

    private Long recipeId;
}
