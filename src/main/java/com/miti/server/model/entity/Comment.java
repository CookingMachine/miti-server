package com.miti.server.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.miti.server.model.dto.CommentDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comm")
@JsonIgnoreProperties(ignoreUnknown = true,
        value = {"commentator", "recipe"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    private Long commentedRecipeId;

    private Long userId;

    @ManyToOne(fetch = FetchType.EAGER)
    private User commentator;

    @ManyToOne(fetch = FetchType.EAGER)
    private Recipe recipe;

    public Comment() {
    }

    public Comment(CommentDTO dto){
        this.comment = dto.getCommentText();
        this.commentedRecipeId = dto.getRecipeId();
        this.userId = dto.getUserId();
    }
}
