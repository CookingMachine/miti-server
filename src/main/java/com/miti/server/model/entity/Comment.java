package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comm")
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"recipe"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;
    private Long recipeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usrId", nullable = false)
    private User commentator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rId", nullable = false)
    private Recipe recipe;

    public Comment() {
    }

    public Comment(String comment, User commentator, Recipe recipe) {
        this.comment = comment;
        this.commentator = commentator;
        this.recipe = recipe;
        this.recipeId = recipe.getId();
    }
}
