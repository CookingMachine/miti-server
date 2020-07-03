package com.miti.server.service;

import com.miti.server.model.entity.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(String id, String name);

    Category addCategory(Category category);

    Category getCategoryByName(String name);

    List<Category> getAllCategories();
}
