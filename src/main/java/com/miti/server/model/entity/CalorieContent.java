package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CALORIE_CONTENT")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"recipe"})
public class CalorieContent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "CALORIES")
  private Long calories;

  @Column(name = "PROTEIN")
  private Long protein;

  @Column(name = "FAT")
  private Long fat;

  @Column(name = "CARBOHYDRATES")
  private Long carbohydrates;

  @OneToMany(mappedBy = "calorie")
  private List<Recipe> recipe;

  public CalorieContent(Long calories, Long protein, Long fat, Long carbohydrates) {
    this.calories = calories;
    this.protein = protein;
    this.fat = fat;
    this.carbohydrates = carbohydrates;
  }
}
