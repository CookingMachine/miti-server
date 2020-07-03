package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"ingredientContextList"})
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ingredient")
    private List<IngredientContext> ingredientContextList;

    public Ingredient() {
    }

    public Ingredient(String name) {
        this.name = name;
    }
}
