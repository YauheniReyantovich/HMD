package org.reyantovich.yauheni.controller;

import org.reyantovich.yauheni.model.pojo.Category;
import org.reyantovich.yauheni.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class IngredientController {

    private static final String EMPTY_STRING = "";

    private CategoryService categoryService;

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public String ingredients(Model model){
        Collection<Category> categories = categoryService.getAllCategories();
        model.addAttribute("allCategories", categories);
        model.addAttribute("categoryForm", new Category());
        return "ingredients";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("categoryForm") Category category, BindingResult bindingResult, Model model){
        if(category.getRusName() != null && !EMPTY_STRING.equals(category.getEngName()) && category.getEngName() != null && !EMPTY_STRING.equals(category.getEngName())) {
            categoryService.addCategory(category);
        }
        return "redirect:/ingredients";
    }

    @Autowired
    IngredientController(CategoryService categoryService){
        this.categoryService = categoryService;}
}
