package org.reyantovich.yauheni.service.impl;

import org.reyantovich.yauheni.dao.model.CategoryDao;
import org.reyantovich.yauheni.dao.model.IngredientDao;
import org.reyantovich.yauheni.model.pojo.Category;
import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.reyantovich.yauheni.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Component
public class IngredientServiceImpl implements IngredientService {

    private final static String LOCALE_RU = "ru";

    private IngredientDao ingredientDao;

    private CategoryDao categoryDao;

    @Override
    public List<Ingredient> getAllIngredients(Locale locale) {
        return ingredientDao.getAllIngredients(locale);
    }

    @Override
    public Map<Category, List<Ingredient>> getCategorisedIngredients(Locale locale) {
        //TODO Изначально нужно доставать все категории и для каждой категории свой набор ингредиентов
        List<Ingredient> ingredients = getAllIngredients(locale);

        Map<String, List<Ingredient>> categorisedIngredients = ingredients.stream().collect(Collectors.groupingBy(Ingredient::getCategory));
        List<Category> categories = categoryDao.getAllCategories();

        Map<Category, List<Ingredient>> sortedIngredients = new TreeMap<>();
        String localizedName;
        for(Category category: categories){
            localizedName = LOCALE_RU.equals(locale.getLanguage()) ? category.getRusName() : category.getEngName();
            sortedIngredients.put(category, categorisedIngredients.get(localizedName));
        }

        return sortedIngredients;
    }

    @Override
    public void addIngredient(Ingredient ingredient, Locale locale) {
        ingredientDao.addIngredient(ingredient, locale);
    }

    @Autowired
    IngredientServiceImpl(IngredientDao ingredientDao, CategoryDao categoryDao){
        this.ingredientDao = ingredientDao;
        this.categoryDao = categoryDao;
    }
}
