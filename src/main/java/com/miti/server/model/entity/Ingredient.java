package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.enums.IngredientCategory;
import com.miti.server.model.dto.IngredientDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"ingredientContextList"})
public class Ingredient {
    @Id
    private String id;

    private String name;

    private IngredientCategory category;

    @OneToMany(mappedBy = "ingredient")
    private List<IngredientContext> ingredientContextList;

    public Ingredient(IngredientDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.category = dto.getCategory();
    }
}
