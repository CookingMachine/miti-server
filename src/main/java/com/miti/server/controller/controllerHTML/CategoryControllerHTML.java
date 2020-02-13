package com.miti.server.controller.controllerHTML;

import com.miti.server.entity.Category;
import com.miti.server.service.CategoryService;
import com.miti.server.service.impl.CategoryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class CategoryControllerHTML {
    private final CategoryService categoryService;

    public CategoryControllerHTML(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categoryHTML")
    public String showAllCategories(Map<String, Object> model) {
        List<Category> categories = categoryService.getAllCategories();
        model.put("categories", categories);
        return "category";
    }


    @PostMapping("/categoryHTML")
    public String addCategory(@RequestParam String name, Map<String, Object> model) {
        categoryService.addCategory(name);

        List<Category> categories = categoryService.getAllCategories();
        model.put("categories", categories);
        return "category";
    }
}
