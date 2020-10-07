package com.miti.server.service.impl;

import com.miti.server.enums.IngredientCategory;
import com.miti.server.model.entity.Ingredient;
import com.miti.server.repository.IngredientRepository;
import com.miti.server.service.IngredientService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IngredientServiceImpl implements IngredientService{
  private final IngredientRepository ingredientRepository;

  @Override
  public Ingredient addIngredient(Ingredient ingredient) {
    if (checkFields(ingredient.getId(), ingredient.getName()))
      return ingredientRepository.save(new Ingredient(
          ingredient.getId(),
          ingredient.getName(),
          ingredient.getCategory()
      ));
    throw new RuntimeException("Name: " + ingredient.getName() + " already exist!");
  }

  @Override
  public void addAllIngredients(List<Ingredient> ingredients) {
    List<Ingredient> _ingredients = new ArrayList<>();
    for (Ingredient ingredient : ingredients) {
      if (checkFields(
          ingredient.getId(),
          ingredient.getName()
      ))
        _ingredients.add(ingredient);
    }
    ingredientRepository.saveAll(_ingredients);
  }

  @Override
  public Ingredient getIngredientById(String ingredientId) {
    return ingredientRepository.findById(ingredientId).orElseThrow(()
        -> new RuntimeException("Ingredient with ingredientId: " + ingredientId + " doesn't exist!"));
  }

  @Override
  public Ingredient getIngredientByName(String name) {
    if (Check.param(name)) {
      Ingredient ingredient = ingredientRepository.getIngredientByName(name);
      if (ingredient != null)
        return ingredient;
      throw new RuntimeException("Ingredient with name: " + name + " doesn't exist!");
    }
    throw new RuntimeException("Name: " + name + " is incorrect!");
  }

  @Override
  public List<Ingredient> getIngredientsByCategory(String categoryName) {
    if (Check.param(categoryName)) {
      IngredientCategory category = IngredientCategory.valueOf(categoryName);
      List<Ingredient> ingredients = ingredientRepository.getIngredientsByCategory(category);
      if (ingredients != null)
        return ingredients;
      throw new RuntimeException("Users with role: " + categoryName + " don't exist!");
    }
    throw new RuntimeException("CategoryName: " + categoryName + " is incorrect!");
  }

  @Override
  public void deleteIngredientById(String ingredientId) {
    ingredientRepository.deleteById(ingredientId);
  }

  public boolean checkFields(String id, String name) {
    if (ingredientRepository.existsById(id))
      return false;
    return !ingredientRepository.existsByName(name);
  }
}
