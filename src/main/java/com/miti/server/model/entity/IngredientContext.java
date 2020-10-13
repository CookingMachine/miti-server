package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.enums.Measure;
import com.sun.org.glassfish.gmbal.NameValue;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ingredient_context_table")
@NoArgsConstructor
@Data
public class IngredientContext {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long amount;
  private Measure measure;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ingredient_id", nullable = false)
  private Ingredient ingredient;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "recipe_id", nullable = false)
  private Recipe recipe;

  public IngredientContext(Long amount, Measure measure, Ingredient ingredient, Recipe recipe) {
    this.amount = amount;
    this.measure = measure;
    this.ingredient = ingredient;
    this.recipe = recipe;
  }
}
