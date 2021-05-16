package com.miti.server.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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
