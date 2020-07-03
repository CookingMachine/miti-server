package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"recipeList"})
public class Category {
    @Id
    private String id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Recipe> recipeList;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
