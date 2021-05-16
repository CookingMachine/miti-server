package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.model.enums.IngredientCategory;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "INGREDIENT")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"contextIngredientList"})
public class Ingredient {

  @Id
  @Column(name = "ID")
  private String id;

  @Column(name = "NAME")
  private String name;

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
