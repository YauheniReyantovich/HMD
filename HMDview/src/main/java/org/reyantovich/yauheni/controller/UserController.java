package org.reyantovich.yauheni.controller;


import org.reyantovich.yauheni.model.pojo.User;
import org.reyantovich.yauheni.service.SecurityService;
import org.reyantovich.yauheni.service.UserService;
import org.reyantovich.yauheni.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("user", "command", userService.findByUsername("Darment"));
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("mvc-dispatcher") User user,
                             ModelMap model) {
        userService.addUser(user);
        model.addAttribute("login", user.getLogin());
        model.addAttribute("password", user.getPassword());
        model.addAttribute("role", user.getRole());

        return "result";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model){
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model){
        return "admin";
    }

    @RequestMapping(value = "/main_page", method = RequestMethod.GET)
    public String mainPage(Model model){
        return "main_page";
    }

    @RequestMapping(value = "/my_profile", method = RequestMethod.GET)
    public String myProfile(Model model){
        return "my_profile";
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}