package org.reyantovich.yauheni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RecommendationController {

    @RequestMapping(value = "/edit_recommendation", method = RequestMethod.GET)
    public String editRecommendation(Model model){
        return "edit_recommendation";
    }

    @RequestMapping(value = "/recommendation", method = RequestMethod.GET)
    public String recommendation(Model model){
        return "recommendation";
    }
}
