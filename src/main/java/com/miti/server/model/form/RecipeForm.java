package com.miti.server.model.form;

import lombok.Data;

@Data
public class RecipeForm {
    private String name;
    private String description;
    private String authorName;
    private String category;
}
