package com.miti.server.entity;

import javax.persistence.*;

@Entity
@Table(name = "comm")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usrId", nullable = false)
    private User commentator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipeId", nullable = false)
    private Recipe recipe;

    public Comment() {
    }

    public Comment(String comment, User commentator, Recipe recipe) {
        this.comment = comment;
        this.commentator = commentator;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
