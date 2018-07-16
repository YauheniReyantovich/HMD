package org.reyantovich.yauheni.controller;


import org.reyantovich.yauheni.pojo.Developer;
import org.reyantovich.yauheni.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DeveloperController {

    private DeveloperService developerService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "developer", method = RequestMethod.GET)
    public ModelAndView developer() {
        return new ModelAndView("developer", "command", developerService.getDeveloper());
    }

    @RequestMapping(value = "/addDeveloper", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("mvc-dispatcher") Developer developer,
                             ModelMap model) {
        developerService.addDeveloper(developer);
        model.addAttribute("id", developer.getId());
        model.addAttribute("firstName", developer.getFirstName());
        model.addAttribute("lastName", developer.getLastName());
        model.addAttribute("specilaty", developer.getSpecialty());
        model.addAttribute("experience", developer.getExperience());

        return "result";
    }

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }
}