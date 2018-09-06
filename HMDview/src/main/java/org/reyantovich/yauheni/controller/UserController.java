package org.reyantovich.yauheni.controller;


import org.reyantovich.yauheni.hmdbase.Developer;
import org.reyantovich.yauheni.model.pojo.User;
import org.reyantovich.yauheni.service.DeveloperService;
import org.reyantovich.yauheni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class DeveloperController {

    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("user", "command", userService.getUser("Darment", "1234"));
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("mvc-dispatcher") User user,
                             ModelMap model) {
        userService.addUser(user);
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("role", user.getRole().toString());

        return "result";
    }

    @Autowired
    public DeveloperController(UserService userService) {
        this.userService = userService;
    }
}