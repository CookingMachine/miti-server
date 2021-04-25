package com.miti.server.model.entity;

import com.miti.server.model.enums.Measure;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "CONTEXT_INGREDIENT")
@NoArgsConstructor
@Data
public class ContextIngredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "AMOUNT")
  private Long amount;

  @Column(name = "MEASURE")
  private Measure measure;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "INGREDIENT_ID", nullable = false)
  private Ingredient ingredient;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "RECIPE_ID", nullable = false)
  private Recipe recipe;

  public ContextIngredient(Long amount, Measure measure, Ingredient ingredient, Recipe recipe) {
    this.amount = amount;
    this.measure = measure;
    this.ingredient = ingredient;
    this.recipe = recipe;
  }
}
