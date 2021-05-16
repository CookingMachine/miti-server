package com.miti.server.controller;

import com.miti.server.api.CategoryService;
import com.miti.server.model.entity.Category;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
