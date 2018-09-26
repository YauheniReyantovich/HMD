package org.reyantovich.yauheni.dao.model;

import org.reyantovich.yauheni.model.pojo.Pizza;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public interface PizzaDao {

    String createNewPizza();

    Pizza getPizza(String id, Locale locale);

    void addIngredient(String id, String ingredientToAdd, Locale locale);

}
