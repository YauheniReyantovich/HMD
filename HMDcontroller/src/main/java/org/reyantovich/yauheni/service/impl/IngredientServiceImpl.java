package org.reyantovich.yauheni.service.impl;

import org.reyantovich.yauheni.dao.model.IngredientDao;
import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.reyantovich.yauheni.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class IngredientServiceImpl implements IngredientService {

    private IngredientDao ingredientDao;

    @Override
    public List<Ingredient> getAllIngredients(Locale locale) {
        return ingredientDao.getAllIngredients(locale);
    }

    @Override
    public void addIngredient(Ingredient ingredient, Locale locale) {
        ingredientDao.addIngredient(ingredient, locale);
    }

    @Autowired
    IngredientServiceImpl(IngredientDao ingredientDao){this.ingredientDao = ingredientDao;}
}
