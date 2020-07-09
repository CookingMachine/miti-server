package com.miti.server.model.dto;

import com.miti.server.enums.IngredientCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {

    private String id;

    private String name;

    private IngredientCategory category;
}
