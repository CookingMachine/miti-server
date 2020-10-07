package com.miti.server.service.impl;

import com.miti.server.model.entity.Category;
import com.miti.server.repository.CategoryRepository;
import com.miti.server.service.CategoryService;
import com.miti.server.util.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        if (checkFields(category.getId(), category.getName())) {
            return new Category(category.getId(), category.getName());
        }
        throw new RuntimeException("CategoryId:" + category.getId() +
                " or categoryName: " + category.getName() + " already exist!");
    }

    @Override
    public void addAllCategories(List<Category> categories) {
        List<Category> _categories = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            if (checkFields(
                    categories.get(i).getId(),
                    categories.get(i).getName()
            ))
                _categories.add(categories.get(i));
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
        if (Check.param(name)) {
            Category category = categoryRepository.getCategoryByName(name);
            if (category != null)
                return category;
            throw new RuntimeException("Category with name " + name + " doesn't exist!");
        }
        throw new RuntimeException("Name: " + name + " is incorrect!");
    }

    @Override
    public void deleteCategoryById(String categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    private boolean checkFields(String categoryId, String name) {
        if (categoryRepository.existsById(categoryId))
            return false;
        if (categoryRepository.existsByName(name))
            return false;
        return true;
    }
}
