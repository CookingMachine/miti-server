package com.miti.server.form;

import lombok.Data;

@Data
public class RecipeForm {
    private String name;
    private String description;
    private String authorName;
    private String category;
}
