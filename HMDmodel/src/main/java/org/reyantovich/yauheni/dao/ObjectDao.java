package org.reyantovich.yauheni.dao;

import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ObjectDao {

    private SessionHolder sessionHolder;

    public HmdObjects addObject(HmdObjectType objectType){
        sessionHolder = sessionHolder.init();
        HmdObjects object = new HmdObjects(UUID.randomUUID(), objectType);
        sessionHolder.saveAndCommit(object);
        sessionHolder.close();
        return object;
    }

    public HmdObjects getObject(UUID id){
        sessionHolder = sessionHolder.init();
        HmdObjects objects = sessionHolder.getSession().get(HmdObjects.class, id);
        sessionHolder.commit();
        sessionHolder.close();
        return objects;
    }

    @Autowired
    public ObjectDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}

}
