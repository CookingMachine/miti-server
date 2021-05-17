package com.miti.server.service.impl;

import com.miti.data.enums.IngredientCategory;
import com.miti.data.model.Ingredient;
import com.miti.data.repository.IngredientRepository;
import com.miti.server.service.IngredientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientServiceImpl implements IngredientService {

  private final IngredientRepository ingredientRepository;

  @Override
  public Ingredient addIngredient(Ingredient ingredient) {
    if (existsByIdAndName(ingredient.getId(), ingredient.getName())) {
      return ingredientRepository.save(new Ingredient(
          ingredient.getId(),
          ingredient.getName(),
          ingredient.getCategory()
      ));
    }
    throw new RuntimeException("Name: " + ingredient.getName() + " already exist!");
  }

  @Override
  public void addAllIngredients(List<Ingredient> ingredients) {
    List<Ingredient> _ingredients = new ArrayList<>();
    for (Ingredient ingredient : ingredients) {
      if (existsByIdAndName(
          ingredient.getId(),
          ingredient.getName()
      )) {
        _ingredients.add(ingredient);
      }
    }
    ingredientRepository.saveAll(_ingredients);
  }

  @Override
  public Ingredient editIngredient(Ingredient newIngredient) {
    return ingredientRepository.findById(newIngredient.getId()).map(ingredient -> {
      ingredient.setName(newIngredient.getName());
      ingredient.setCategory(newIngredient.getCategory());
      return ingredientRepository.save(ingredient);
    }).orElseThrow(()
        -> new RuntimeException("Ingredient with id: " + newIngredient.getId() + " doesn't exist"));
  }

  @Override
  public Ingredient getIngredientById(String ingredientId) {
    return ingredientRepository.findById(ingredientId).orElseThrow(()
        -> new RuntimeException(
        "Ingredient with ingredientId: " + ingredientId + " doesn't exist!"));
  }

  @Override
  public Ingredient getIngredientByName(String name) {
    if (!StringUtils.isEmpty(name)) {
      Ingredient ingredient = ingredientRepository.getIngredientByName(name);
      if (ingredient != null) {
        return ingredient;
      }
      throw new RuntimeException("Ingredient with name: " + name + " doesn't exist!");
    }
    throw new RuntimeException("Name: " + name + " is incorrect!");
  }

  @Override
  public List<Ingredient> getAllIngredients() {
    return ingredientRepository.findAll();
  }

  @Override
  public List<Ingredient> getIngredientsByCategory(String categoryName) {
    if (!StringUtils.isEmpty(categoryName)) {
      IngredientCategory category = IngredientCategory.valueOf(categoryName);
      List<Ingredient> ingredients = ingredientRepository.getIngredientsByCategory(category);
      if (ingredients != null) {
        return ingredients;
      }
      throw new RuntimeException("Users with role: " + categoryName + " don't exist!");
    }
    throw new RuntimeException("CategoryName: " + categoryName + " is incorrect!");
  }

  @Override
  public void deleteIngredientById(String ingredientId) {
    ingredientRepository.deleteById(ingredientId);
  }

  public boolean existsByIdAndName(String id, String name) {
    if (ingredientRepository.existsById(id)) {
      return false;
    }
    return !ingredientRepository.existsByName(name);
  }
}
