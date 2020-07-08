package com.miti.server.controller.controllerRest;

import com.miti.server.model.entity.Category;
import com.miti.server.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories () {
        return categoryService.getAllCategories();
    }

    @PostMapping("/addCategory")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @GetMapping("/getCategoryById")
    public Category getCategoryById(@RequestParam String categoryId) {
        return categoryService.getCategoryById(categoryId);
    }
}
