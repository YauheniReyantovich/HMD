package org.reyantovich.yauheni.controller;

import org.reyantovich.yauheni.model.pojo.Pizza;
import org.reyantovich.yauheni.service.IngredientService;
import org.reyantovich.yauheni.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class PizzaController {

    private static final String  piz = "407390BC-E667-4219-AE4D-4C5B14CFD1CD";

    private PizzaService pizzaService;

    private IngredientService ingredientService;

    @RequestMapping(value = "/create_pizza", method = RequestMethod.GET)
    public String createPizza(Model model, Locale locale){

//        String id = pizzaService.createNewPizza();
//        model.addAttribute("pizzaId", id);
//        model.addAttribute("pizzasIngr", Collections.EMPTY_LIST);
//        model.addAttribute("totalCost", "0");
//        model.addAttribute("ingredients", ingredientService.getCategorisedIngredients(locale));

        model.addAttribute("pizzaId", piz);
        Pizza pizza = pizzaService.getPizza(piz, locale);
        model.addAttribute("pizzasIngr", pizza.getIngredients());
        model.addAttribute("totalCost", String.valueOf(pizza.getTotalCost()));
        model.addAttribute("ingredients", ingredientService.getCategorisedIngredients(locale));

        return "create_pizza";
    }

    @RequestMapping(value = "/create_pizza/{id}", method = RequestMethod.GET)
    public String pizzaView(@PathVariable String id, Model model, Locale locale){

        model.addAttribute("pizzaId", id);
        Pizza pizza = pizzaService.getPizza(id, locale);
        model.addAttribute("pizzasIngr", pizza.getIngredients());
        model.addAttribute("totalCost", String.valueOf(pizza.getTotalCost()));
        model.addAttribute("ingredients", ingredientService.getCategorisedIngredients(locale));

        return "create_pizza";
    }

    @RequestMapping(value = "/addIngrToPizza/{id}", method = RequestMethod.POST)
    public String addIngrToPizza(@PathVariable String id, Model model, Locale locale, @RequestParam("ingredientToAdd") String ingredientToAdd){

//        pizzaService.addIngredient(id, ingredientToAdd, locale);

        return "redirect:/create_pizza/{id}";
    }

    @RequestMapping(value = "/pizza_view", method = RequestMethod.GET)
    public String pizzaView( Model model){
        return "pizza_view";
    }

    @Autowired
    public PizzaController(PizzaService pizzaService, IngredientService ingredientService){
        this.pizzaService = pizzaService;
        this.ingredientService = ingredientService;
    }

}
