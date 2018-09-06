package org.reyantovich.yauheni.pojo;

import org.reyantovich.yauheni.enums.UserRoles;

public class User {

    private String login;
    private String password;
    private UserRoles role;


    public String getlogin() {
        return login;
    }

    public void setlogin(String name) {
        this.login = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
