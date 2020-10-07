package com.miti.server.service;

import com.miti.server.model.entity.Category;

import java.util.List;

public interface CategoryService {
  Category addCategory(Category category);
  void addAllCategories(List<Category> categories);

  Category getCategoryById(String categoryId);
  Category getCategoryByName(String name);

  void deleteCategoryById(String categoryId);
}
