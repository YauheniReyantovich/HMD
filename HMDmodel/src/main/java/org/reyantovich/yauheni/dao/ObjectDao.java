package org.reyantovich.yauheni.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.reyantovich.yauheni.hmdbase.HmdObjectType;
import org.reyantovich.yauheni.hmdbase.HmdObjects;
import org.reyantovich.yauheni.runner.SessionHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ObjectDao {

    private SessionHolder sessionHolder;

    public HmdObjects addObject(HmdObjectType objectType){
        sessionHolder = sessionHolder.init();
        HmdObjects object = new HmdObjects(objectType);
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

    public List<UUID> getAllObjectsOfObjectType(UUID objectTypeId) {

        try {
            sessionHolder.init();
            Session session = sessionHolder.getSession();
            HmdObjectType objectType = session.get(HmdObjectType.class, objectTypeId);
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<HmdObjects> cr = cb.createQuery(HmdObjects.class);
            Root<HmdObjects> root = cr.from(HmdObjects.class);
            cr.select(root).where(cb.equal(root.get("objectType"), objectType));

            Query<HmdObjects> query = session.createQuery(cr);
            List<HmdObjects> results = query.getResultList();

            List<UUID> res = new ArrayList<>();
            for (HmdObjects result : results) {
                res.add(result.getObjectId());
            }
            return res;
        } catch (Exception e) {
            sessionHolder.getSession().getTransaction().rollback();
            e.printStackTrace();
        } finally {
            sessionHolder.close();
        }
        return null;
    }

    @Autowired
    public ObjectDao(SessionHolder sessionHolder){this.sessionHolder = sessionHolder;}

}
