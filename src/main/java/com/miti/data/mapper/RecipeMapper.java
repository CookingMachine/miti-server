package com.miti.data.mapper;

import com.miti.data.model.Recipe;
import com.miti.server.api.request.RecipeRequest;
import com.miti.server.api.response.RecipeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RecipeMapper {

  @Mappings({
      @Mapping(source = "calorieContent", target = "calorie"),
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "createDate", ignore = true),
      @Mapping(target = "commentList", ignore = true),
      @Mapping(target = "favouriteUsers", ignore = true),
      @Mapping(target = "rating", ignore = true),
      @Mapping(target = "restaurants", ignore = true),
      @Mapping(source = "image", target = "imageUrl")
  })
  Recipe recipeRequestToRecipeModel(RecipeRequest recipeRequest);

  @Mappings({
      @Mapping(source = "calorie", target = "calorieContent"),
      @Mapping(source = "imageUrl", target = "image")
  })
  RecipeResponse recipeModelToRecipeResponse(Recipe recipe);
}

