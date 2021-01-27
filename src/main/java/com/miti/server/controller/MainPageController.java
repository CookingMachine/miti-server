package com.miti.server.controller;

import com.miti.server.model.entity.Category;
import com.miti.server.model.entity.Recipe;
import com.miti.server.service.CategoryService;
import com.miti.server.service.RecipeService;
import com.sun.org.apache.xpath.internal.operations.String;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MainPageController {

  private final CategoryService categoryService;
  private final RecipeService recipeService;

  @GetMapping("/mainPage")
  public void getMainPageElements(HttpServletResponse res) throws IOException {
    res.setCharacterEncoding("UTF-8");
    List<Category> categories = categoryService.getAllCategories();
    List<Recipe> recipes = recipeService.getAllRecipes();
    java.lang.String elements = categories.toString() +
      "\n" +
      recipes.toString();
    res.getWriter().println(elements);
  }

  @GetMapping("/fastAndDelicious")
  public void getFastAndDelicious(HttpServletResponse res) throws IOException {
    res.setCharacterEncoding("UTF-8");
    List<Recipe> recipes = recipeService.getRecipesByTimeLessThanEqual(600);
    res.getWriter().println(recipes.toString());
  }
}
