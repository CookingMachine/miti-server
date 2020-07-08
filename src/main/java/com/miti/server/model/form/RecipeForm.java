package com.miti.server.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeForm {

    private String name;

    @Size(max = 4000)
    private String description;

    private Long authorId;

    private String categoryId;
}
