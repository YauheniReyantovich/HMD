package org.reyantovich.yauheni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PizzaController {

    @RequestMapping(value = "/create_pizza", method = RequestMethod.GET)
    public String createPizza(Model model){
        return "create_pizza";
    }

    @RequestMapping(value = "/pizza_view", method = RequestMethod.GET)
    public String pizzaView(Model model){
        return "pizza_view";
    }

}
