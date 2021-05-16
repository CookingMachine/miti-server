package com.miti.server.model.entity;

import java.util.Date;
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
@Table(name = "COMMENT")
@NoArgsConstructor
@Data
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "COMMENT")
  private String comment;

  @Column(name = "CREATE_DATE")
  private Date createDate;

  @Column(name = "EDIT_DATE")
  private Date editDate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "USER_ID", nullable = false)
  private User commentator;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "RECIPE_ID", nullable = false)
  private Recipe recipe;

  public Comment(String comment, User commentator, Recipe recipe) {
    this.comment = comment;
    this.commentator = commentator;
    this.recipe = recipe;

    this.createDate = new Date();
    this.editDate = null;
  }
}
