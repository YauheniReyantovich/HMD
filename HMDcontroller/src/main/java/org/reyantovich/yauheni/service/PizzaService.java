package org.reyantovich.yauheni.service;

import org.reyantovich.yauheni.model.pojo.Pizza;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public interface PizzaService {

    Pizza getPizza(String id, Locale locale);

    String createNewPizza();

    void addIngredient(String id, String ingredientToAdd, Locale locale);

}
