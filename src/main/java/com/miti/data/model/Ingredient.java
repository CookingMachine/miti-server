package com.miti.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.data.enums.IngredientCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "INGREDIENT")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"contextIngredientList"})
public class Ingredient {

  @Id
  @Column(name = "ID")
  private String id;

  @Column(name = "NAME")
  private String name;

  @Enumerated
  @Column(name = "CATEGORY")
  private IngredientCategory category;

  @OneToMany(mappedBy = "ingredient")
  private List<ContextIngredient> contextIngredientList;

  public Ingredient(String id, String name, IngredientCategory category) {
    this.id = id;
    this.name = name;
    this.category = category;
  }
}
