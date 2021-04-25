package com.miti.server.controller;

import com.miti.server.model.entity.Category;
import com.miti.server.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
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

  @GetMapping("/getCategoryByName/{name}")
  public Category getCategoryByName(@PathVariable String name) {
    return categoryService.getCategoryByName(name);
  }

  @DeleteMapping("/{id}")
  public String deleteCategoryById(@PathVariable String id) {
    categoryService.deleteCategoryById(id);
    return "Done!";
  }
}
