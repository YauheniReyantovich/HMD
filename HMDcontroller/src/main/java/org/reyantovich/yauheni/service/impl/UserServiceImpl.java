package org.reyantovich.yauheni.service.impl;

import org.reyantovich.yauheni.dao.UserDao;
import org.reyantovich.yauheni.model.pojo.User;
import org.reyantovich.yauheni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User getUser(String login, String password) {
        return userDao.getUser(login, password);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Autowired
    public UserServiceImpl(UserDao userDao){this.userDao = userDao;}
}
