package org.reyantovich.yauheni.service.impl;

import org.reyantovich.yauheni.dao.model.PizzaDao;
import org.reyantovich.yauheni.model.pojo.IngredientHolder;
import org.reyantovich.yauheni.model.pojo.Pizza;
import org.reyantovich.yauheni.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class PizzaServiceImpl implements PizzaService {

    private static final Double MINIMAL_COST_OF_PIZZA = 0.;

    private PizzaDao pizzaDao;

    @Override
    public Pizza getPizza(String id, Locale locale) {
        Pizza pizza = pizzaDao.getPizza(id, locale);

        Double sum = MINIMAL_COST_OF_PIZZA;
        for(IngredientHolder ingredient: pizza.getIngredients()){
            Integer numberOfIngredients = ingredient.getNumberOfIngredients();
            Double costOfIngredient = Double.valueOf(ingredient.getIngredient().getCost());
            sum += numberOfIngredients * costOfIngredient;
        }
        pizza.setTotalCost(sum);

        return pizza;

    }

    @Override
    public String createNewPizza() {
        return pizzaDao.createNewPizza();
    }

    @Override
    public void addIngredient(String id, String ingredientToAdd, Locale locale) {
        pizzaDao.addIngredient(id, ingredientToAdd, locale);
    }

    @Autowired
    PizzaServiceImpl(PizzaDao pizzaDao){
        this.pizzaDao = pizzaDao;
    }
}
