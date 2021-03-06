package org.reyantovich.yauheni.dao;

import org.reyantovich.yauheni.hmdbase.HmdAttributes;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttributeDao {

    private SessionHolder sessionHolder;

    public void addAttribute(HmdObjectType objectType, String name){
        sessionHolder = sessionHolder.init();
        HmdAttributes attribute = new HmdAttributes(objectType, name);
        sessionHolder.saveAndCommit(attribute);
        sessionHolder.close();
    }

    @Autowired
    public AttributeDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}

}
