package com.miti.server.controller;

import com.miti.server.model.entity.Category;
import com.miti.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping("/addCategory")
  public Category addCategory(@RequestBody Category category) {
    return categoryService.addCategory(category);
  }

  @GetMapping("/getCategoryById")
  public Category getCategoryById(@RequestParam String categoryId) {
    return categoryService.getCategoryById(categoryId);
  }

  @GetMapping("/getCategoryByName")
  public Category getCategoryByName(@RequestParam String name) {
    return categoryService.getCategoryByName(name);
  }

  @DeleteMapping("/deleteCategoryById")
  public String deleteCategoryById(@RequestParam String categoryId) {
    categoryService.deleteCategoryById(categoryId);
    return "Done!";
  }
}
