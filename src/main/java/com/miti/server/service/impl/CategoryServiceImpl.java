package com.miti.server.service.impl;

import com.miti.data.model.Category;
import com.miti.data.repository.CategoryRepository;
import com.miti.server.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category addCategory(Category category) {
    if (existsByCategoryIdAndName(category.getId(), category.getName())) {
      return categoryRepository.save(new Category(category.getId(), category.getName()));
    }
    throw new RuntimeException("CategoryId:" + category.getId() +
        " or categoryName: " + category.getName() + " already exist!");
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  @Override
  public void addAllCategories(List<Category> categories) {
    List<Category> _categories = new ArrayList<>();
    for (Category category : categories) {
      if (existsByCategoryIdAndName(
          category.getId(),
          category.getName()
      )) {
        _categories.add(category);
      }
    }
    categoryRepository.saveAll(_categories);
  }

  @Override
  public Category getCategoryById(String categoryId) {
    return categoryRepository.findById(categoryId).orElseThrow(()
        -> new RuntimeException("Category with id: " + categoryId + " doesn't exist!"));
  }

  @Override
  public Category getCategoryByName(String name) {
    if (!StringUtils.isEmpty(name)) {
      Category category = categoryRepository.getCategoryByName(name);
      if (category != null) {
        return category;
      }
      throw new RuntimeException("Category with name " + name + " doesn't exist!");
    }
    throw new RuntimeException("Name: " + name + " is incorrect!");
  }

  @Override
  public void deleteCategoryById(String categoryId) {
    categoryRepository.deleteById(categoryId);
  }

  private boolean existsByCategoryIdAndName(String categoryId, String name) {
    if (categoryRepository.existsById(categoryId)) {
      return false;
    }
    return !categoryRepository.existsByName(name);
  }
}
