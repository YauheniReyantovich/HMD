package org.reyantovich.yauheni.validator;

import org.reyantovich.yauheni.model.pojo.User;
import org.reyantovich.yauheni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
         return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if(user.getLogin().length() < 4 || user.getLogin().length() > 32){
            errors.rejectValue("login", "security.userform.username.size");
        }

        if(userService.findByUsername(user.getLogin()) != null){
            errors.rejectValue("login", "security.userform.username.duplicate");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if(user.getPassword().length() < 6){
            errors.rejectValue("password", "security.userform.password.size");
        }

        if(!user.getConfirmPassword().equals(user.getPassword())){
            errors.rejectValue("confirmPassword", "security.userform.password.different");
        }
    }

    @Autowired
    public UserValidator(UserService userService){this.userService = userService;}
}
