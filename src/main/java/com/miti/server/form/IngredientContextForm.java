package com.miti.server.form;

import lombok.Data;

@Data
public class IngredientContextForm {
    private double count;
    private String flag;
    private String ingredientName;
    private Long recipeId;
}
