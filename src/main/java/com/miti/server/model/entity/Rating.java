package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "RATING")
@Data
@NoArgsConstructor
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "VALUE")
  private int ratingValue;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "RECIPE_ID", nullable = false)
  private Recipe recipe;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "USER_ID", nullable = false)
  private User user;

  public Rating(int ratingValue, Recipe recipe, User user) {
    this.ratingValue = ratingValue;
    this.recipe = recipe;
    this.user = user;
  }
}
