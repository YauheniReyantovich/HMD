package org.reyantovich.yauheni.service;

import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public interface IngredientService {

    List<Ingredient> getAllIngredients(Locale locale);

    void addIngredient(Ingredient ingredient, Locale locale);

}
