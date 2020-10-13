package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CATEGORY")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"recipeList"})
public class Category {
  @Id
  @Column(name = "ID")
  private String id;
  @Column(name = "NAME")
  private String name;

  @OneToMany(mappedBy = "category")
  private List<Recipe> recipeList;

  public Category(String id, String name) {
    this.id = id;
    this.name = name;
  }
}
