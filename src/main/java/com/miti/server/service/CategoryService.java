package com.miti.server.service;

import com.miti.data.model.Category;
import java.util.List;

public interface CategoryService {

  Category addCategory(Category category);

  List<Category> getAllCategories();

  void addAllCategories(List<Category> categories);

  Category getCategoryById(String categoryId);

  Category getCategoryByName(String name);

  void deleteCategoryById(String categoryId);
}
