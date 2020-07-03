package com.miti.server.model.form;

import lombok.Data;

@Data
public class CommentForm {
    private String commentText;
    private Long recipeId;
    private Long userId;
}
