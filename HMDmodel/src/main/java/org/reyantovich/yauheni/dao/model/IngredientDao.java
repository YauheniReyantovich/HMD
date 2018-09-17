package org.reyantovich.yauheni.dao.model;

import org.reyantovich.yauheni.model.pojo.Ingredient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public interface IngredientDao {

    List<Ingredient> getAllIngredients(Locale locale);

    void addIngredient(Ingredient ingredient, Locale locale);

}
