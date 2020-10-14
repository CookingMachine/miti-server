package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RECIPE")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"commentList", "contextIngredientList", "favouriteUsers"})
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "NAME")
  private String name;
  @Size(max = 4000)
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "CREATE_DATE")
  private Date createDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "USER_ID", nullable = false)
  private User author;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CATEGORY_ID", nullable = false)
  private Category category;

  @OneToMany(mappedBy = "recipe")
  private List<Comment> commentList;

  @OneToMany(mappedBy = "recipe")
  private List<ContextIngredient> contextIngredientList;

  @ManyToMany
  private List<User> favouriteUsers;

  public Recipe(String name, String description, User author, Category category) {
    this.name = name;
    this.description = description;
    this.author = author;
    this.category = category;

    this.createDate = new Date();
  }
}
