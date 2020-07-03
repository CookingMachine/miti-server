package com.miti.server.model.form;

import lombok.Data;

@Data
public class IngredientContextForm {
    private double count;
    private String flag;
    private String ingredientName;
    private Long recipeId;
}
