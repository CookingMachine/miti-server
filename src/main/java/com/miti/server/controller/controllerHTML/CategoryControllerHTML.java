package com.miti.server.controller.controllerHTML;

import com.miti.server.model.dto.CategoryDTO;
import com.miti.server.model.entity.Category;
import com.miti.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryControllerHTML {
    private final CategoryService categoryService;

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
        CategoryDTO categoryDTO = new CategoryDTO();
        model.addAttribute("categoryForm", categoryDTO);

        return "category";
    }

    @RequestMapping(value = {"/category"}, method = RequestMethod.POST)
    public String addCategory(Model model, @ModelAttribute("categoryForm") CategoryDTO categoryDTO) {
        String id = categoryDTO.getId();
        String name = categoryDTO.getName();

        if (name != null && name.length() > 0) {
            categoryService.addCategory(id, name);

            return "redirect:/categoryList";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "category";
    }
}
