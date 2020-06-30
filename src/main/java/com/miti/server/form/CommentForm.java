package com.miti.server.form;

import lombok.Data;

@Data
public class CommentForm {
    private String commentText;
    private Long recipeId;
    private Long userId;
}
