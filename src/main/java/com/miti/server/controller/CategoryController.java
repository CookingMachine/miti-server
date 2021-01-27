package com.miti.server.controller;

import com.miti.server.model.entity.Category;
import com.miti.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {
  private final CategoryService categoryService;

  @PostMapping("/category/addCategory")
  public Category addCategory(@RequestBody Category category) {
    return categoryService.addCategory(category);
  }

  @GetMapping("/category/getAllCategories")
  public List<Category> getAllCategories(){ return categoryService.getAllCategories(); }

  @GetMapping("/category/getCategoryById")
  public Category getCategoryById(@RequestParam(name = "id") String categoryId) {
    return categoryService.getCategoryById(categoryId);
  }

  @GetMapping("/category/getCategoryByName")
  public Category getCategoryByName(@RequestParam(name = "name") String name) {
    return categoryService.getCategoryByName(name);
  }

  @DeleteMapping("/category/deleteCategoryById")
  public String deleteCategoryById(@RequestParam(name = "id") String categoryId) {
    categoryService.deleteCategoryById(categoryId);
    return "Done!";
  }
}
