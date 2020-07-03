package com.miti.server.controller.controllerHTML;

import com.miti.server.model.entity.Category;
import com.miti.server.form.CategoryForm;
import com.miti.server.service.CategoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CategoryControllerHTML {
    private CategoryService categoryService;

    public CategoryControllerHTML(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Value("${error.message}")
    private String errorMessage;


    @RequestMapping(value = {"/categoryList"}, method = RequestMethod.GET)
    public String categoryList(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "lists/categoryList";
    }

    @RequestMapping(value = {"/category"}, method = RequestMethod.GET)
    public String showAddCategoryPage(Model model) {
        CategoryForm categoryForm = new CategoryForm();
        model.addAttribute("categoryForm", categoryForm);

        return "category";
    }

    @RequestMapping(value = {"/category"}, method = RequestMethod.POST)
    public String addCategory(Model model, @ModelAttribute("categoryForm") CategoryForm categoryForm) {
        String name = categoryForm.getName();

        if (name != null && name.length() > 0) {
            categoryService.addCategory(name);

            return "redirect:/categoryList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "category";
    }
}
