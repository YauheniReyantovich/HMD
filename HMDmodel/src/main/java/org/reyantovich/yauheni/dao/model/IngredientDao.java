package org.reyantovich.yauheni.dao.model;

import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public interface IngredientDao {

    Ingredient getIngredientWithValues(HmdObjects ingredientInDB, Locale locale);

    List<Ingredient> getIngredients(List<HmdObjects> ingredientsInDB, Locale locale);

    List<Ingredient> getAllIngredients(Locale locale);

    void addIngredient(Ingredient ingredient, Locale locale);

}
