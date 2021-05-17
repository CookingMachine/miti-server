package com.miti.server.controller;

import com.miti.data.model.Category;
import com.miti.server.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
@AllArgsConstructor
public class CategoryController {

  private final CategoryService categoryService;

  @PostMapping("")
  public Category addCategory(@RequestBody Category category) {
    return categoryService.addCategory(category);
  }

  @GetMapping("")
  public List<Category> getAllCategories() {
    return categoryService.getAllCategories();
  }

  @GetMapping("/{id}")
  public Category getCategoryById(@PathVariable String id) {
    return categoryService.getCategoryById(id);
  }

  @GetMapping("/getByName/{name}")
  public Category getCategoryByName(@PathVariable String name) {
    return categoryService.getCategoryByName(name);
  }

  @DeleteMapping("/{id}")
  public String deleteCategoryById(@PathVariable String id) {
    categoryService.deleteCategoryById(id);

    return "Successfully removed CATEGORY with id [" + id + "]";
  }
}
