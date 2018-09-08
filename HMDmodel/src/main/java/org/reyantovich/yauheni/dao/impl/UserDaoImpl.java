package org.reyantovich.yauheni.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.reyantovich.yauheni.HqlService;
import org.reyantovich.yauheni.attributesIds.UserObjectType.UserAttributes;
import org.reyantovich.yauheni.dao.UserDao;
import org.reyantovich.yauheni.hmdbase.HmdAttributes;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.hmdbase.HmdValues;
import org.reyantovich.yauheni.model.pojo.User;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoImpl implements UserDao {

    private SessionHolder sessionHolder;

    @Override
    public User findByUsername(String username){

        sessionHolder = sessionHolder.init();
        Session session = sessionHolder.getSession();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(HqlService.SQL_PROPERTIES);
        try{
            Query sqlQuery = session.createQuery(resourceBundle.getString(HqlService.GET_TWO_OBJECT_ATTRIBUTES_BY_ANOTHER_ATTRIBUTE));

            sqlQuery.setParameter("value", username);
            sqlQuery.setParameter("givenAttr", UserAttributes.LOGIN);
            sqlQuery.setParameter("requiredAttr1", UserAttributes.PASSWORD);
            sqlQuery.setParameter("requiredAttr2", UserAttributes.ROLE);
            sqlQuery.setParameter("objOTId", UserAttributes.USER);

            Object[] result = (Object[]) sqlQuery.getSingleResult();

            User user = new User();
            user.setLogin(username);
            user.setPassword((String) result[0]);
            user.setRole((String) result[1]);

            return user;

        }
        catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            sessionHolder.close();
        }
        return null;
    }

    @Override
    public User getUser(String login, String password) {

        sessionHolder = sessionHolder.init();
        Session session = sessionHolder.getSession();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(HqlService.SQL_PROPERTIES);
        List results;
        User user = null;
        try {

            Query sqlQuery = session.createQuery(resourceBundle.getString(HqlService.GET_USER_ATTRIBUTES_BY_LOGIN_AND_PASSWORD));

            sqlQuery.setParameter("login", login);
            sqlQuery.setParameter("password", password);
            sqlQuery.setParameter("loginAttrId", UserAttributes.LOGIN);
            sqlQuery.setParameter("passwordAttrId", UserAttributes.PASSWORD);
            sqlQuery.setParameter("userOTId", UserAttributes.USER);

            results = sqlQuery.getResultList();

            if(results != null && results.size() != 0){
                user = new User();
                Object[] attributeAndValue;
                HmdAttributes attribute;
                String value;
                for(Object result: results){
                    attributeAndValue = ((Object[]) result);
                    attribute = (HmdAttributes) attributeAndValue[0];
                    value = (String) attributeAndValue[1];
                    switch (attribute.getAttrId().toString()){
                        case UserAttributes.LOGIN:
                            user.setLogin(value); break;
                        case UserAttributes.PASSWORD:
                            user.setPassword(value); break;
                        case UserAttributes.ROLE:
                            user.setRole(value);
                    }
                }
            }

            sessionHolder.commit();
        }
        catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            sessionHolder.close();
        }
        return user;
    }

    @Override
    public User getUser(String login, String password, String... attrs) {
        return null;
    }

    @Override
    public void addUser(User user) {
        if(user != null) {
            sessionHolder = sessionHolder.init();
            Session session = sessionHolder.getSession();
            HmdObjectType userObjectType = session.get(HmdObjectType.class, UserAttributes.USER_UUID);
            HmdObjects object = new HmdObjects(UUID.randomUUID(), userObjectType);
            sessionHolder.save(object);

            if(user.getLogin() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, UserAttributes.LOGIN_UUID), user.getLogin())
                );
            }
            if(user.getPassword() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, UserAttributes.PASSWORD_UUID), user.getPassword())
                );
            }
            if(user.getRole() != null) {
                sessionHolder.save(
                        new HmdValues(object, session.get(HmdAttributes.class, UserAttributes.ROLE_UUID), user.getRole())
                );
            }

            sessionHolder.commit();
            sessionHolder.close();
        }
    }

    @Autowired
    public UserDaoImpl(SessionHolder sessionHolder){
        this.sessionHolder = sessionHolder;
    }
}
