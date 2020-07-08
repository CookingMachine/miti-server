package com.miti.server.service.impl;

import com.miti.server.model.dto.CategoryDTO;
import com.miti.server.model.entity.Category;
import com.miti.server.repository.CategoryRepository;
import com.miti.server.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(String id, String name) {
        return addCategory(new Category(new CategoryDTO(id, name)));
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public Category getCategoryById(String categoryId) { return categoryRepository.findById(categoryId).orElseThrow(()
            -> new RuntimeException("Category with id: " + categoryId + " doesn't exists")); }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
