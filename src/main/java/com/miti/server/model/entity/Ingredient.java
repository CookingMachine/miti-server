package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @OneToMany(mappedBy = "ingredient")
    private List<IngredientContext> ingredientContextList;

    public Ingredient(IngredientDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }
}
