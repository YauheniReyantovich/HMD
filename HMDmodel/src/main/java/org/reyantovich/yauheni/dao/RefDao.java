package org.reyantovich.yauheni.dao;

import org.reyantovich.yauheni.hmdbase.HmdAttributes;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.hmdbase.HmdRefs;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class RefDao {

    private SessionHolder sessionHolder;

    public void addRefs(HmdObjects object, Map<UUID, HmdObjects> refs) {

        for (Map.Entry<UUID, HmdObjects> entry : refs.entrySet()) {
            sessionHolder.save(
                    new HmdRefs(sessionHolder.getSession().get(HmdAttributes.class, entry.getKey()), object, entry.getValue())
            );
        }
    }

    @Autowired
    public RefDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}

}
