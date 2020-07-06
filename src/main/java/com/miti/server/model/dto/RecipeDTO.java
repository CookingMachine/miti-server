package com.miti.server.model.dto;

import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

    private String name;

    private String description;

    private User author;

    private Category category;
}
