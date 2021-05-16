package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.model.enums.Kitchen;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RECIPE")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"commentList", "contextIngredientList",
    "favouriteUsers", "rating"})
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
  private Date createDate;

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

  public Recipe(String name, String description, User author, Category category, Kitchen kitchen,
      int time, CalorieContent calorie) {
    this.name = name;
    this.description = description;
    this.author = author;
    this.category = category;
    this.kitchen = kitchen;
    this.time = time;
    this.calorie = calorie;
    this.rating = null;

    this.createDate = new Date();
  }
}
