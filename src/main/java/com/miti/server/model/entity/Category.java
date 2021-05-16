package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Override
  public String toString() {
    return "{\n" +
        "\"id\": " + this.getId() + ",\n" +
        "\"name\": \"" + this.getName() + "\"\n" +
        "}";
  }
}
