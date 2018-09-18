package org.reyantovich.yauheni.dao;

import org.reyantovich.yauheni.hmdbase.HmdAttributes;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.hmdbase.HmdValues;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class ValueDao {

    private SessionHolder sessionHolder;

//    public void addValue(HmdObjects object, UUID attributeId, String value){
//        if (value != null) {
//            sessionHolder.save(new HmdValues(object, sessionHolder.getSession().get(HmdAttributes.class, attributeId), value));
//        }
//    }

    public void addValues(HmdObjects objects, Map<UUID, String> values){

        for(Map.Entry<UUID, String> entry : values.entrySet()){
            if(entry.getValue() != null){
                sessionHolder.save(new HmdValues(objects, sessionHolder.getSession().get(HmdAttributes.class, entry.getKey()), entry.getValue()));
            }
        }

    }

    @Autowired
    public ValueDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}

}
