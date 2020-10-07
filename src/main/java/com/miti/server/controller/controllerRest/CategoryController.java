package com.miti.server.controller.controllerRest;

import com.miti.server.model.entity.Category;
import com.miti.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
    private final CategoryService categoryService;

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
