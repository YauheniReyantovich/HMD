package org.reyantovich.yauheni.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ObjectTypeDao {

    private SessionHolder sessionHolder;

    public void addObjectType(String name){
        sessionHolder.init();
        HmdObjectType objectType = new HmdObjectType(UUID.randomUUID(), name);
        sessionHolder.saveAndCommit(objectType);
        sessionHolder.close();
    }

    public HmdObjectType getObjectType(UUID id){
        sessionHolder.init();
        HmdObjectType objectType = sessionHolder.getSession().get(HmdObjectType.class, id);
        sessionHolder.commit();
        sessionHolder.close();
        return objectType;
    }

    @Autowired
    public ObjectTypeDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}
}
