package org.reyantovich.yauheni.controller;

import org.reyantovich.yauheni.model.pojo.Category;
import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.reyantovich.yauheni.model.pojo.Layer;
import org.reyantovich.yauheni.service.CategoryService;
import org.reyantovich.yauheni.service.IngredientService;
import org.reyantovich.yauheni.service.LayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class IngredientController {

    private static final String EMPTY_STRING = "";

    private CategoryService categoryService;

    private LayerService layerService;

    private IngredientService ingredientService;

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public String ingredients(Model model, Locale locale){
        model.addAttribute("allCategories", categoryService.getAllCategories());
        model.addAttribute("categoryForm", new Category());
        model.addAttribute("allLayers", layerService.getAllLayers());
        model.addAttribute("layerForm", new Layer());
        model.addAttribute("allIngredients", ingredientService.getAllIngredients(locale));
        model.addAttribute("ingredientForm", new Ingredient());
        return "ingredients";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.POST)
    public String addCategory(@ModelAttribute("categoryForm") Category category, BindingResult bindingResult, Model model){
        if(category.getRusName() != null && !EMPTY_STRING.equals(category.getEngName()) && category.getEngName() != null && !EMPTY_STRING.equals(category.getEngName())) {
            categoryService.addCategory(category);
        }
        return "redirect:/ingredients";
    }

    @RequestMapping(value = "/addLayer", method = RequestMethod.POST)
    public String addLayer(@ModelAttribute("layerForm") Layer layer, BindingResult bindingResult, Model model, @RequestParam("layerInput") Integer[] layerChances){
        if( layer.getEngName() != null && !EMPTY_STRING.equals(layer.getEngName()) &&
            layer.getRusName() != null && !EMPTY_STRING.equals(layer.getRusName()) &&
            layer.getMaxIngredients() != null && layer.getMaxIngredients() != 0 &&
            layerChances != null) {

            layerService.addLayer(layer, layerChances);
        }

        return "redirect:/ingredients";
    }

    @RequestMapping(value = "/addIngredient", method = RequestMethod.POST)
    public String addIngredient(@ModelAttribute("ingredientForm") Ingredient ingredient, BindingResult bindingResult, Model model, Locale locale){
        ingredientService.addIngredient(ingredient, locale);

        return "redirect:/ingredients";
    }

    @Autowired
    IngredientController(CategoryService categoryService, LayerService layerService, IngredientService ingredientService){
        this.categoryService = categoryService;
        this.layerService = layerService;
        this.ingredientService = ingredientService;
    }
}
