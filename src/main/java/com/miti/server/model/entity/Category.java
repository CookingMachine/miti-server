package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "category_table")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"recipeList"})
public class Category {
  @Id
  private String id;
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Recipe> recipeList;

  public Category(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
