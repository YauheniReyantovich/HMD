package org.reyantovich.yauheni.model.pojo;

import org.reyantovich.yauheni.model.enums.UserRoles;

public class User {

    private String login;
    private String password;
    private UserRoles role;
    transient private String confirmPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role.toString();
    }

    public void setRole(UserRoles userRole){this.role = userRole;}

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public void setRole(String role) {
        this.role = UserRoles.get(role);
    }
}
