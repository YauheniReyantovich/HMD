package org.reyantovich.yauheni.service;

import org.reyantovich.yauheni.model.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUser(String login, String password);

    void addUser(User user);

}
