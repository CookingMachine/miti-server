package com.miti.server.api;

import com.miti.server.model.entity.Category;
import java.util.List;

public interface CategoryService {

  Category addCategory(Category category);

  List<Category> getAllCategories();

  void addAllCategories(List<Category> categories);

  Category getCategoryById(String categoryId);

  Category getCategoryByName(String name);

  void deleteCategoryById(String categoryId);
}
