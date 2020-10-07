package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comment_table")
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true, value = {"commentator", "recipe"})
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String comment;

  @ManyToOne(fetch = FetchType.EAGER)
  private User commentator;

  @ManyToOne(fetch = FetchType.EAGER)
  private Recipe recipe;

  public Comment(String comment, User commentator, Recipe recipe) {
    this.comment = comment;
    this.commentator = commentator;
    this.recipe = recipe;
  }
}
