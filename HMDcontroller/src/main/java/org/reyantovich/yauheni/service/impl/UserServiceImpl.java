package org.reyantovich.yauheni.service.impl;

import org.reyantovich.yauheni.dao.UserDao;
import org.reyantovich.yauheni.model.enums.UserRoles;
import org.reyantovich.yauheni.model.pojo.User;
import org.reyantovich.yauheni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User getUser(String login, String password) {
        return userDao.getUser(login, password);
    }

    @Override
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(UserRoles.ROLE_REGISTERED);
        userDao.addUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }


    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
