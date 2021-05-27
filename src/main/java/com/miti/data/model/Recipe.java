package com.miti.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.data.enums.Kitchen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "RECIPE")
@JsonIgnoreProperties(ignoreUnknown = true, value = {"commentList", "contextIngredientList",
    "favouriteUsers", "rating", "restaurants"})
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

  @Column(name = "TIME")
  private int time;

  @Column(name = "CREATE_DATE")
  private LocalDateTime createDate;

  @Enumerated
  @Column(name = "KITCHEN")
  private Kitchen kitchen;

  @Column(name = "IMAGE")
  private String imageUrl;

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

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "CALORIE_ID", nullable = false)
  private CalorieContent calorie;

  @ManyToMany
  private List<User> favouriteUsers;

  @OneToMany(mappedBy = "recipe")
  private List<Rating> rating;

  @ManyToMany
  @JoinTable(
      name = "restaurant_recipe",
      inverseJoinColumns = @JoinColumn(name = "restaurant_id"),
      joinColumns = @JoinColumn(name = "recipe_id"))
  private List<Restaurant> restaurants;

  public Recipe(String name, String description, User author, Category category, Kitchen kitchen, int time,
                CalorieContent calorie) {
    this.name = name;
    this.description = description;
    this.author = author;
    this.category = category;
    this.kitchen = kitchen;
    this.time = time;
    this.calorie = calorie;
    this.rating = null;

    this.createDate = LocalDateTime.now();
  }
}
