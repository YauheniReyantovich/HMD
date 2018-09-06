package org.reyantovich.yauheni.dao;

import org.reyantovich.yauheni.hmdbase.HmdAttributes;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttributeDao {

    private SessionHolder sessionHolder;

    public void addAttribute(HmdObjectType objectType, String name){
        sessionHolder = sessionHolder.init();
        HmdAttributes attribute = new HmdAttributes(UUID.randomUUID(), objectType, name);
        sessionHolder.saveAndCommit(attribute);
        sessionHolder.close();
    }

    public HmdAttributes getAttribute(String id){
        sessionHolder = sessionHolder.init();
        HmdAttributes attribute = sessionHolder.getSession().get(HmdAttributes.class, UUID.fromString(id));
        sessionHolder.commit();
        sessionHolder.close();
        return attribute;
    }

    @Autowired
    public AttributeDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}

}
