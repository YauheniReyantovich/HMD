package org.reyantovich.yauheni.service;

import org.reyantovich.yauheni.model.pojo.Category;
import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public interface IngredientService {

    List<Ingredient> getAllIngredients(Locale locale);

    Map<Category, List<Ingredient>> getCategorisedIngredients(Locale locale);

    void addIngredient(Ingredient ingredient, Locale locale);

}
