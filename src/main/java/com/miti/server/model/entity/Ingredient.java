package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.enums.IngredientCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ingredient_table")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"ingredientContextList"})
public class Ingredient {
  @Id
  private String id;
  private String name;
  private IngredientCategory category;

  @OneToMany(mappedBy = "ingredient")
  private List<IngredientContext> ingredientContextList;

  public Ingredient(String id, String name, IngredientCategory category) {
    this.id = id;
    this.name = name;
    this.category = category;
  }
}
