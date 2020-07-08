package com.miti.server.model.dto;

import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

    private String name;

    @Size(max = 4000)
    private String description;

    private User author;

    private Category category;
}
