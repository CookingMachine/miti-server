package com.miti.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "CATEGORY")
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

  @Override
  public String toString() {
    return "{\n" +
        "\"id\": " + this.getId() + ",\n" +
        "\"name\": \"" + this.getName() + "\"\n" +
        "}";
  }
}
