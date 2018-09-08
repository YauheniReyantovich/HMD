package org.reyantovich.yauheni.dao;

import org.reyantovich.yauheni.model.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserDao {

    User getUser(String login, String password);

    User getUser(String login, String password, String... attrs);

    void addUser(User user);

    User findByUsername(String username);

}
